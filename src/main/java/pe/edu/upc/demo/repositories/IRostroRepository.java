package pe.edu.upc.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.demo.entities.Rostro;

import java.util.List;

@Repository
public interface IRostroRepository extends JpaRepository<Rostro, Integer> {

    @Query(value = " SELECT \n " +
            "                            f.nombre_forma AS FormaDeRostro,\n " +
            "                            COUNT(R.id_rostro) AS CantidadRostros\n " +
            "                         FROM \n " +
            "                            Forma F\n " +
            "                         LEFT JOIN \n " +
            "                            Rostro r ON f.id_forma = r.id_forma\n " +
            "                         GROUP BY \n " +
            "                            f.nombre_forma\n " +
            " \t\t\t\t\t\t\tLIMIT 3 ", nativeQuery = true)
    public List<String[]> cantidadRostroForma();
}
