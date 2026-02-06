package co.com.unir.msbookscatalogservice.infra.adapter.rest.mapper;

import co.com.unir.msbookscatalogservice.domain.model.Book;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import co.com.unir.msbookscommon.DTOs.BookDTO;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    public static final BookMapper INSTANCE = Mappers.getMapper( BookMapper.class );

    @Mapping(target = "rating",source = "book", qualifiedByName = "calcRating")
    BookDTO bookToDTO(Book book);

    default List<BookDTO> bookListToBookDTO(List<Book> list){
        return list.stream().map(this::bookToDTO).toList();
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book bookToEntity(BookDTO bookDTO,@MappingTarget Book bookEntity);

    @Named("calcRating")
    static int calcRating(Book book) {
        Long pos = book.getPositiveReviews();
        Long neg = book.getNegativeReviews();
        float p = Math.max(0f, Math.min(100f, (((pos - neg) * 100f) / pos)));
        int stars = 1 + (int) Math.floor(p / 20f);
        return Math.min(stars, 5);
    }
}
