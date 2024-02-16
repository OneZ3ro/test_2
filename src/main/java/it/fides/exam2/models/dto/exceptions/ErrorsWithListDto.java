package it.fides.exam2.models.dto.exceptions;

import java.util.Date;
import java.util.List;

public record ErrorsWithListDto(String message, Date timestamp, List<String> errorList) {
}
