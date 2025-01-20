package br.com.fiap.cliente.controller;

import br.com.fiap.cliente.domain.ClienteEntity;
import br.com.fiap.cliente.usecase.ClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteUseCase clienteUseCase;


    @Operation(summary = "Coleta todos os usuários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de clientes encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<Collection<ClienteDTO>> findAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteUseCase.findAllUsers());
    }

    @Operation(summary = "Procura um cliente pelo ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteEntity.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clienteUseCase.findClienteById(id));
    }

    @Operation(summary = "Cria um novo cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteEntity.class))),
            @ApiResponse(responseCode = "400", description = "Input inválido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<ClienteDTO> criaCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteUseCase.criarCliente(clienteDTO));
    }

    @Operation(summary = "Atualiza cliente existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteEntity.class))),
            @ApiResponse(responseCode = "400", description = "Input inválido"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateUser(@PathVariable UUID id, @Valid @RequestBody ClienteDTO clienteDTO) {
        var result = clienteUseCase.updateCliente(id, clienteDTO);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Deleta cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaCliente(@PathVariable UUID id) {
        clienteUseCase.deletaCliente(id);
        return ResponseEntity.noContent().build();
    }
}
