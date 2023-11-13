package org.greentech.backend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.greentech.backend.dto.request.ParameterRequestDto;
import org.greentech.backend.dto.response.ParameterResponseDto;
import org.greentech.backend.service.ParameterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping(path = "/parameters")
@RequiredArgsConstructor
public class ParameterController {
    private final ParameterService parameterService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParameterResponseDto> createParameter(@RequestBody ParameterRequestDto requestDto) {
        ParameterResponseDto responseDto = parameterService.create(requestDto);

        return ResponseEntity
                .created(URI.create("/api/parameters/" + responseDto.getId()))
                .body(responseDto);
    }

    @GetMapping("/{parameterId}")
    public ResponseEntity<ParameterResponseDto> findParameterById(@PathVariable Integer parameterId) {
        return ResponseEntity
                .ok(parameterService.findById(parameterId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ParameterResponseDto>> findAllParameters() {
        return ResponseEntity
                .ok(parameterService.findAll());
    }

    @PatchMapping("/{parameterId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParameterResponseDto> updateParameter(@PathVariable Integer parameterId,
                                                                @RequestBody ParameterRequestDto requestDto) {
        return ResponseEntity
                .ok(parameterService.update(parameterId, requestDto));
    }

    @DeleteMapping("/{parameterId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParameterById(@PathVariable Integer parameterId) {
        parameterService.deleteById(parameterId);
    }
}
