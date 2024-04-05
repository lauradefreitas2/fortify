package org.example;

public class ProtocoloController {
    private ProtocoloService protocoloService;

    public ProtocoloController() {
        this.protocoloService = new ProtocoloService();
    }

    public void abrirProtocolo(ProtocoloModel protocolo) {
        protocoloService.abrirProtocolo(protocolo);
    }

    public void consultarProtocolo(int numeroProtocolo) {
        protocoloService.consultarProtocolo(numeroProtocolo);
    }

    public void deletarProtocolo(int numeroProtocolo) {
        protocoloService.deletarProtocolo(numeroProtocolo);
    }

    public void editarProtocolo(ProtocoloModel protocolo) {
        protocoloService.editarProtocolo(protocolo);
    }

    public void adicionarProtocolo(ProtocoloModel protocolo) {
    }
}
