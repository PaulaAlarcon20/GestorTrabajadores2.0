package com.calendario.trabajadores.repository.vehiculo;

import com.calendario.trabajadores.model.database.Usuario;
import com.calendario.trabajadores.model.database.Vehiculo;
import com.calendario.trabajadores.model.dto.vehiculo.VehiculoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IVehiculoRepository extends JpaRepository<Vehiculo, Long>, CustomVehiculoRepository {

    @Query("SELECT v FROM vehiculos v WHERE v.matricula = :p")
    Vehiculo findVehiculoByMatricula(@Param("p")String matricula);
}
