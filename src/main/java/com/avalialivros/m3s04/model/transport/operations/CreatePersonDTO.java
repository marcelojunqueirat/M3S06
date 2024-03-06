package com.avalialivros.m3s04.model.transport.operations;

import com.avalialivros.m3s04.model.enums.NotificationTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePersonDTO(@NotBlank String name,
                              @NotBlank String email,
                              @NotBlank String phone,
                              @NotBlank String password,
                              @NotNull NotificationTypeEnum notificationType) {
}
