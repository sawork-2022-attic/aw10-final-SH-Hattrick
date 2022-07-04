package com.micropos.carts.mapper;

import com.micropos.carts.dto.ItemDto;
import com.micropos.carts.model.Item;
import org.mapstruct.Mapper;
import com.micropos.products.model.*;

import java.util.Collection;

@Mapper
public interface ItemMapper {

    Collection<ItemDto> toItemsDto(Collection<Item> items);

    Collection<Item> toItems(Collection<ItemDto> items);

    Item toItem(ItemDto itemDto);

    ItemDto toItemDto(Item item);

    Product map(Object value);
}
