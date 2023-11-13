package org.greentech.backend.service.implementation;

import lombok.RequiredArgsConstructor;
import org.greentech.backend.data.repository.ParameterRepository;
import org.greentech.backend.dto.request.ParameterRequestDto;
import org.greentech.backend.dto.response.ParameterResponseDto;
import org.greentech.backend.entity.Parameter;
import org.greentech.backend.exception.DataConflictException;
import org.greentech.backend.exception.DataMissingException;
import org.greentech.backend.service.ParameterService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class ParameterServiceImpl implements ParameterService {
    private static final String PARAMETER_NAME_TAKEN = "Параметр с заданным именем уже существует";
    private static final String PARAMETER_ID_NOT_FOUND = "Параметр с переданным id не существует";
    private static final String PARAMETER_HAS_RELATED_ENTITIES = "Параметр связан с товарами";

    private final ParameterRepository parameterRepository;

    @Override
    @Transactional
    public ParameterResponseDto create(ParameterRequestDto requestDto) {
        Parameter parameterToSave = requestDto.toEntity();
        return ParameterResponseDto.fromEntity(
                saveInternal(parameterToSave));
    }

    @Override
    public ParameterResponseDto findById(Integer id) {
        return ParameterResponseDto.fromEntity(
                findByIdInternal(id));
    }

    @Override
    public List<ParameterResponseDto> findAll() {
        return parameterRepository.findAll(Sort.by("name").ascending())
                .stream().map(ParameterResponseDto::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public ParameterResponseDto update(Integer id, ParameterRequestDto requestDto) {
        Parameter patch = requestDto.toEntity();
        Parameter parameterToUpdate = findByIdInternal(id);

        parameterToUpdate.setName(patch.getName());

        return ParameterResponseDto.fromEntity(
                saveInternal(parameterToUpdate));
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        try {
            parameterRepository.deleteById(id);
            parameterRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DataConflictException(PARAMETER_HAS_RELATED_ENTITIES);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }


    private Parameter saveInternal(Parameter parameterToSave) {
        try {
            return parameterRepository.saveAndFlush(parameterToSave);
        } catch (DataIntegrityViolationException e) {
            throw new DataConflictException(PARAMETER_NAME_TAKEN);
        }
    }

    private Parameter findByIdInternal(Integer parameterId) {
        return parameterRepository.findById(parameterId)
                .orElseThrow(() -> new DataMissingException(PARAMETER_ID_NOT_FOUND));
    }
}
