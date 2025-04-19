package converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Date;
import java.time.LocalDate;
// El conversor se aplique a todas las entidades que lo necesitam
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        // Convierte LocalDate a java.sql.Date
        return (localDate == null) ? null : Date.valueOf(localDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        // Convierte java.sql.Date a LocalDate
        return (date == null) ? null : date.toLocalDate();
    }
}

