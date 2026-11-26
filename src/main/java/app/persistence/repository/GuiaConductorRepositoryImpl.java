package app.persistence.repository;

import app.domain.GuiaConductor;
import app.persistence.mapper.GuiaConductorMapper;
import app.service.outputport.GuiaConductorRepository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GuiaConductorRepositoryImpl implements GuiaConductorRepository {
    private final List<GuiaConductor> guiaConductors;

    public GuiaConductorRepositoryImpl() {
        this.guiaConductors = new ArrayList<>();
    }

    public GuiaConductorRepositoryImpl(Connection coneccion, GuiaConductorMapper guiaConductorMapper, GuiaConductor guiaConductors) {
        this.guiaConductors = Collections.singletonList(guiaConductors);
    }


    @Override
    public GuiaConductor guardarGuiaConductor(GuiaConductor guia) {
        this.guiaConductors.add(guia);
        return guia;
    }

    @Override
    public Optional<GuiaConductor> buscarPorId(int idPersona) {
        for (GuiaConductor guiaConductor : this.guiaConductors){
            if (guiaConductor.getIdPrestador()== idPersona){
                return Optional.of(guiaConductor);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<GuiaConductor> listarTodos() {
        return Collections.unmodifiableList(this.guiaConductors);
    }

    @Override
    public Optional<GuiaConductor> actualizarGuiaConductor(GuiaConductor guia) {
        for (int i = 0; i < this.guiaConductors.size(); i++) {
            if (this.guiaConductors.get(i).getIdPrestador() == guia.getIdPersona()) {
                this.guiaConductors.set(i, guia);
                return Optional.of(guia);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean eliminarGuiaConductor(int idPersona) {
        return this.guiaConductors.removeIf(guiaConductor -> guiaConductor.getIdPersona()==idPersona);
    }
}
