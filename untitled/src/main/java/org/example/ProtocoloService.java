package org.example;

import java.util.ArrayList;
import java.util.List;

public class ProtocoloService {
    private final List<ProtocoloModel> protocolos;

    public ProtocoloService() {
        this.protocolos = new ArrayList<>();
    }

    public void abrirProtocolo(ProtocoloModel protocolo) {
        protocolos.add(protocolo);
        System.out.println("Protocolo aberto com sucesso.");
    }

    public void consultarProtocolo(int numeroProtocolo) {
        for (ProtocoloModel protocolo : protocolos) {
            if (protocolo.getNumeroProtocolo() == numeroProtocolo) {
                System.out.println("Protocolo encontrado:");
                System.out.println(protocolo);
                return;
            }
        }
        System.out.println("Protocolo não encontrado.");
    }

    public void deletarProtocolo(int numeroProtocolo) {
        for (ProtocoloModel protocolo : protocolos) {
            if (protocolo.getNumeroProtocolo() == numeroProtocolo) {
                protocolos.remove(protocolo);
                System.out.println("Protocolo deletado com sucesso.");
                return;
            }
        }
        System.out.println("Protocolo não encontrado.");
    }

    public void editarProtocolo(ProtocoloModel protocolo) {
        for (ProtocoloModel p : protocolos) {
            if (p.getNumeroProtocolo() == protocolo.getNumeroProtocolo()) {
                p.setCliente(protocolo.getCliente());
                p.setDataAbertura(protocolo.getDataAbertura());
                p.setDescricao(protocolo.getDescricao());
                p.setTipoProtocolo(protocolo.getTipoProtocolo());
                System.out.println("Protocolo editado com sucesso.");
                return;
            }
        }
        System.out.println("Protocolo não encontrado.");
    }
}
