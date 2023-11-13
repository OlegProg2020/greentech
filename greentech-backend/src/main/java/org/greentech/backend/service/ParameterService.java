package org.greentech.backend.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.greentech.backend.dto.request.ParameterRequestDto;
import org.greentech.backend.dto.response.ParameterResponseDto;

import java.util.List;

public interface ParameterService {

    ParameterResponseDto create(@Valid ParameterRequestDto requestDto);

    ParameterResponseDto findById(@Min(value = 1, message = "id должен быть >= 1") Integer id);

    List<ParameterResponseDto> findAll();

    ParameterResponseDto update(@Min(value = 1, message = "id должен быть >= 1") Integer id,
                                @Valid ParameterRequestDto requestDto);

    void deleteById(@Min(value = 1, message = "id должен быть >= 1") Integer id);
}
