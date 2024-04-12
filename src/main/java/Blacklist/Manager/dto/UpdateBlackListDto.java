package Blacklist.Manager.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateBlackListDto {
    @NotEmpty(message = "itemName should not be blank")
    private String itemName;

    @NotEmpty(message = "category should not be blank")
    private String category;

    @NotEmpty(message = "Reasons should not be blank")
    private String reason;
}
