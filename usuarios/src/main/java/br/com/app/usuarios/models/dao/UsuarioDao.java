package br.com.app.usuarios.models.dao;

import br.com.app.usuarios.models.entity.Usuarios;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuarios, Long> {

    public Usuarios findByUsername(String username);


}
