package ru.clevertec.userservice.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateJournalistRequest extends UpdateUserRequest {

}
