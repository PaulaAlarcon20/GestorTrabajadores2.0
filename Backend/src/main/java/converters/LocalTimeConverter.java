package converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.sql.Time;
import java.time.LocalTime;

@Converter(autoApply = true)  // Esto hace que el conversor se aplique a todas las entidades que lo necesiten
public class LocalTimeConverter implements AttributeConverter<LocalTime, Time> {

    @Override
    public Time convertToDatabaseColumn(LocalTime localTime) {
        return (localTime == null) ? null : Time.valueOf(localTime);  // Convierte LocalTime a java.sql.Time
    }

    @Override
    public LocalTime convertToEntityAttribute(Time time) {
        return (time == null) ? null : time.toLocalTime();  // Convierte java.sql.Time a LocalTime
    }
}

