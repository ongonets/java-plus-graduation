package ru.practicum.ewm.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.controller.UserClient;
import ru.practicum.ewm.dto.*;
import ru.practicum.ewm.dto.params.CommentParams;
import ru.practicum.ewm.mapper.CommentMapper;
import ru.practicum.ewm.model.Comment;
import ru.practicum.ewm.repository.CommentRepository;
import ru.practicum.ewm.exception.AccessForbiddenException;
import ru.practicum.ewm.exception.ConflictDataException;
import ru.practicum.ewm.exception.NotFoundException;
import ru.practicum.ewm.model.Event;
import ru.practicum.ewm.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserClient userClient;
    private final EventRepository eventRepository;
    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public CommentDto createComment(ParamEventDto params, NewCommentRequest request) {
        UserShortDto author = getUser(params.getUserId());
        Event event = getEvent(params.getEventId());
        Comment comment = commentMapper.mapToComment(request, author, event);
        comment = commentRepository.save(comment);
        log.info("Comment {} added to event {}", comment.getText(), event.getTitle());
        return commentMapper.mapToCommentDto(comment);
    }

    @Override
    @Transactional
    public void deleteComment(CommentParams params) {
        long authorId = params.getUserId();
        Comment comment = getComment(params.getCommentId());
        validateCommentAuthor(authorId, comment);
        commentRepository.delete(comment);
        log.info("Comment {} was deleted  by user {}", comment.getText(), authorId);
    }

    @Override
    public List<CommentDto> findEventComment(ParamEventDto params) {
        Event event = getUserEvent(params);
        List<Comment> comments = commentRepository.findAllByEvent(event);
        return commentMapper.mapToCommentDto(comments);
    }

    @Override
    public List<CommentDto> findUserComments(long userId) {
        List<Comment> comments = commentRepository.findAllByAuthorId(userId);
        return commentMapper.mapToCommentDto(comments);
    }

    @Override
    public CommentDto updateComment(CommentParams params, NewCommentRequest request) {
        Comment comment = getComment(params.getCommentId());
        validateCommentAuthor(params.getUserId(), comment);
        validateCommentDate(comment);
        commentMapper.update(comment, request);
        commentRepository.save(comment);
        log.info("Comment ID = {} updated", comment.getId());
        return commentMapper.mapToCommentDto(comment);
    }

    private void validateCommentAuthor(long userId, Comment comment) {
        if (userId != comment.getAuthorId()) {
            log.error("User with ID = {} has no rights to delete comment ID = {}", userId, comment.getId());
            throw new AccessForbiddenException("No rights to delete comment");
        }

    }

    private void validateCommentDate(Comment comment) {
        if (comment.getCreated().plusHours(1).isBefore(LocalDateTime.now())) {
            log.error("Comment ID = {} is not available for change now", comment.getId());
            throw new ConflictDataException(
                    String.format("Comment ID = %d is not available for change now", comment.getId()));
        }

    }

    private Comment getComment(long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> {
                    log.error("Not found comment with ID = {}", commentId);
                    return new NotFoundException(String.format("Not found comment with ID = %d", commentId));
                });
    }

    private UserShortDto getUser(long userId) {
        try {
            return userClient.findShortUsers(List.of(userId)).getFirst();
        } catch (FeignException e) {
            log.error("Not found user with ID = {}", userId);
            throw new NotFoundException(String.format("Not found user with ID = %d", userId));
        }
    }

    private Event getEvent(long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> {
                    log.error("Not found event with ID = {}", eventId);
                    return new NotFoundException(String.format("Not found event with ID = %d", eventId));
                });
    }

    private Event getUserEvent(ParamEventDto paramEventDto) {
        long eventId = paramEventDto.getEventId();
        Event event = getEvent(eventId);
        if (event.getInitiatorId() != paramEventDto.getUserId()) {
            log.error("Event with ID = {} is not found", eventId);
            throw new NotFoundException(
                    String.format("Not found event with ID = %d", eventId));
        }
        return event;
    }

}
