package pe.edu.upc.demo.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.demo.dtos.EstiloItemDTO;
import pe.edu.upc.demo.entities.EstiloItem;
import pe.edu.upc.demo.serviceinterfaces.IEstiloItemService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estilosItems")
@PreAuthorize("hasAuthority('USUARIO') or hasAuthority('ADMIN')")
public class EstiloItemController {
    @Autowired
    private IEstiloItemService eS;

    @GetMapping
    @PreAuthorize("hasAuthority('USUARIO') or hasAuthority('ADMIN')")
    public List<EstiloItemDTO> listar(){
        return eS.list().stream().map(x->{
            ModelMapper m=new ModelMapper();
            return m.map(x, EstiloItemDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping
    @PreAuthorize("hasAuthority('USUARIO') or hasAuthority('ADMIN')")
    public void insertar(@RequestBody EstiloItemDTO dto){
        ModelMapper m=new ModelMapper();
        EstiloItem e=m.map(dto,EstiloItem.class);
        eS.insert(e);
    }
    @GetMapping ("/{id}")
    @PreAuthorize("hasAuthority('USUARIO') or hasAuthority('ADMIN')")
    public EstiloItemDTO listarId(@PathVariable("id") Integer id) {
        ModelMapper m = new ModelMapper();
        EstiloItemDTO dto = m.map(eS.listId(id), EstiloItemDTO.class);
        return dto;
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO') or hasAuthority('ADMIN')")
    public void modificar(@RequestBody EstiloItemDTO dto){
        ModelMapper m=new ModelMapper();
        EstiloItem v=m.map(dto,EstiloItem.class);
        eS.update(v);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO') or hasAuthority('ADMIN')")
    public void eliminar(@PathVariable("id") Integer id){
        eS.delete(id);
    }

}
