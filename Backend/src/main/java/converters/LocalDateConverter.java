package converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Date;
import java.time.LocalDate;

@Converter(autoApply = true)  // Esto hace que el conversor se aplique a todas las entidades que lo necesiten
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return (localDate == null) ? null : Date.valueOf(localDate);  // Convierte LocalDate a java.sql.Date
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return (date == null) ? null : date.toLocalDate();  // Convierte java.sql.Date a LocalDate
    }
}

