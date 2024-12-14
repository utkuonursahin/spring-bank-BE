package me.utku.springbank.mapper;

import me.utku.springbank.dto.card.CardDto;
import me.utku.springbank.dto.card.UpdateCardSettingsRequest;
import me.utku.springbank.generic.BaseMapper;
import me.utku.springbank.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CardMapper extends BaseMapper<Card> {
    Card toEntity(CardDto cardDto);

    @Mapping(target = "isOpenToInternet", source = "openToInternet")
    @Mapping(target = "isOpenToInternationalTransactions", source = "openToInternationalTransactions")
    @Mapping(target = "isOpenToContactlessPayments", source = "openToContactlessPayments")
    CardDto toDto(Card card);

    Card updateEntity(@MappingTarget Card oldEntity, Card newEntity);

    @Mapping(target = "openToInternet", source = "isOpenToInternet")
    @Mapping(target = "openToInternationalTransactions", source = "isOpenToInternationalTransactions")
    @Mapping(target = "openToContactlessPayments", source = "isOpenToContactlessPayments")
    Card updateEntity(@MappingTarget Card oldEntity, UpdateCardSettingsRequest updateCardSettingsRequest);
}