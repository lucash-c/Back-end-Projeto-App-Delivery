package com.lucashcampos.projetodelivery.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.lucashcampos.projetodelivery.domain.Endereco;
import com.lucashcampos.projetodelivery.domain.Loja;
import com.lucashcampos.projetodelivery.domain.Usuario;

public class LojaDTO {
    private Integer id;
    private String name;
    private String whatsapp;
    private EnderecoDTO endereco;
    private ConfiguracoesDTO configuracoes;
    private Boolean isOpen;
    private Boolean isActive;
    private List<String> especialidade;
    private List<Usuario> usuarios;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public ConfiguracoesDTO getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(ConfiguracoesDTO configuracoes) {
        this.configuracoes = configuracoes;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<String> getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(List<String> especialidade) {
        this.especialidade = especialidade;
    }
    
    public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public static class EnderecoDTO {
        private String cep;
        private String logradouro;
        private String bairro;
        private String cidade;
        private String estado;
        private String numero;
        private String complemento;

        // Getters and Setters

        public String getCep() {
            return cep;
        }

        public void setCep(String cep) {
            this.cep = cep;
        }

        public String getLogradouro() {
            return logradouro;
        }

        public void setLogradouro(String logradouro) {
            this.logradouro = logradouro;
        }

        public String getBairro() {
            return bairro;
        }

        public void setBairro(String bairro) {
            this.bairro = bairro;
        }

        public String getCidade() {
            return cidade;
        }

        public void setCidade(String cidade) {
            this.cidade = cidade;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getComplemento() {
            return complemento;
        }

        public void setComplemento(String complemento) {
            this.complemento = complemento;
        }
    }

    public static class ConfiguracoesDTO {
        private List<FreteDTO> frete;
        private Integer prazoRetirada;
        private Integer limiteDistanciaKm;

        // Getters and Setters

        public List<FreteDTO> getFrete() {
            return frete;
        }

        public void setFrete(List<FreteDTO> frete) {
            this.frete = frete;
        }

        public Integer getPrazoRetirada() {
            return prazoRetirada;
        }

        public void setPrazoRetirada(Integer prazoRetirada) {
            this.prazoRetirada = prazoRetirada;
        }

        public Integer getLimiteDistanciaKm() {
            return limiteDistanciaKm;
        }

        public void setLimiteDistanciaKm(Integer limiteDistanciaKm) {
            this.limiteDistanciaKm = limiteDistanciaKm;
        }
    }

    public static class FreteDTO {
        private Integer km;
        private Double valor;
        private Integer prazo;

        // Getters and Setters

        public Integer getKm() {
            return km;
        }

        public void setKm(Integer km) {
            this.km = km;
        }

        public Double getValor() {
            return valor;
        }

        public void setValor(Double valor) {
            this.valor = valor;
        }

        public Integer getPrazo() {
            return prazo;
        }

        public void setPrazo(Integer prazo) {
            this.prazo = prazo;
        }
    }

    // Mapping method from Loja to LojaDTO

    public static LojaDTO fromLoja(Loja loja) {
        LojaDTO dto = new LojaDTO();
        dto.setId(loja.getId());
        dto.setName(loja.getNomeFantasia());
        dto.setWhatsapp(loja.getWhatsapp());
        dto.setEndereco(fromEndereco(loja.getEndereco()));
        dto.setConfiguracoes(fromConfiguracoes(loja));
        dto.setIsOpen(loja.getIsOpen());
        dto.setIsActive(loja.getIsActive());
        dto.setEspecialidade(loja.getEspecialidades().stream().map(Enum::name).collect(Collectors.toList()));
        return dto;
    }

    private static EnderecoDTO fromEndereco(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        EnderecoDTO dto = new EnderecoDTO();
        dto.setCep(endereco.getCep());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        dto.setNumero(endereco.getNumero());
        dto.setComplemento(endereco.getComplemento());
        return dto;
    }

    private static ConfiguracoesDTO fromConfiguracoes(Loja loja) {
        ConfiguracoesDTO dto = new ConfiguracoesDTO();
        dto.setFrete(loja.getFretes().stream().map(f -> {
            FreteDTO freteDto = new FreteDTO();
            freteDto.setKm(f.getKm());
            freteDto.setValor(f.getValor());
            freteDto.setPrazo(f.getPrazo());
            return freteDto;
        }).collect(Collectors.toList()));
        dto.setPrazoRetirada(loja.getPrazoRetirada());
        dto.setLimiteDistanciaKm(loja.getLimiteDistanciaKm());
        return dto;
    }
}
