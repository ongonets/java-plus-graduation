package ru.practicum.ewm;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.dto.HitDto;
import ru.practicum.ewm.dto.StatDto;

import java.util.List;


@FeignClient(name = "stats-service")
public interface StatClient {

    @PostMapping("/hit")
    void hit(@RequestBody HitDto hitDto);

    @GetMapping("/stats")
    List<StatDto> stat(@RequestParam String start,
                       @RequestParam String end,
                       @RequestParam(required = false) List<String> uris,
                       @RequestParam(defaultValue = "false") Boolean unique);
}
