package app.ddd.gsandwiches.sandwich.api.mappers;

import app.ddd.gsandwiches.sandwich.api.dto.request.CreateSandwichDto;
import app.ddd.gsandwiches.sandwich.domain.Sandwich;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Description;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Name;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.Price;
import app.ddd.gsandwiches.sandwich.domain.valueobjects.SandwichId;
import app.ddd.gsandwiches.shared.mapper.Mapper;

public class CreateSandwichDtoToSandwichMapper implements Mapper<CreateSandwichDto, Sandwich> {

    @Override
    public Class<CreateSandwichDto> sourceType() {
        return CreateSandwichDto.class;
    }

    @Override
    public Class<Sandwich> targetType() {
        return Sandwich.class;
    }

    @Override
    public Sandwich map(CreateSandwichDto source) {
        return new Sandwich(
                new SandwichId(source.sandwichId()),
                new Name(source.name()),
                new Price(source.price()),
                new Description(source.description()));
    }

}
