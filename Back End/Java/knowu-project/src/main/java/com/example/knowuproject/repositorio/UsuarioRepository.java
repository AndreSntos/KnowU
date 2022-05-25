package com.example.knowuproject.repositorio;

import com.example.knowuproject.modelo.Usuario;
import com.example.knowuproject.requisicao.UsuarioCadastroSimples;
import com.example.knowuproject.requisicao.UsuarioLoginSimples;
import com.example.knowuproject.requisicao.UsuarioNomeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    public Usuario findByUsuario(String usuario);

    public Usuario findByEmail(String email);

    public Usuario findByCodigoRecuperaSenha(Integer codigo);

    // Querys para tests
    @Query("select new com.example.knowuproject.requisicao.UsuarioLoginSimples (u.usuario, u.senha) from Usuario u")
    List<UsuarioLoginSimples> findLoginUsuario();

    @Query("select new com.example.knowuproject.requisicao.UsuarioNomeResponse (u.nome, u.usuario) from Usuario u where u.idUsuario = ?1")
    List<UsuarioNomeResponse> listarNomeAndUsuario(Integer idUsuario);


    @Modifying
    @Transactional
    @Query(value = "update usuario SET avaliacao = ?2 where id_usuario = ?1", nativeQuery = true)
    void atualizarAvaliacao(Integer idUsuario, Integer avaliacao);

    @Modifying
    @Transactional
    @Query(value = "update usuario SET codigo_recupera_senha = ?2 where id_usuario = ?1", nativeQuery = true)
    void atualizarCodigoDeRecuperaçãoSenha(Integer idUsuario, Integer codigo);

    @Modifying
    @Transactional
    @Query(value = "update usuario SET data_nascimento = ?2, genero = ?3, email = ?4, senha = ?5, nome = ?6, usuario = ?7, descricao= ?8 where id_usuario = ?1", nativeQuery = true)
    void atualizarDadosDaConta(Integer idUsuario, String dataNascimento, String genero, String email, String senha, String nome, String usuario, String descricao);



}
