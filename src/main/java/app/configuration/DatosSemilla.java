package app.configuration;

import app.domain.GuiaConductor;
import app.domain.Itinerario;
import app.domain.Turista;
import app.domain.enums.NivelEstimulacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// TEMPORAL: datos que reflejan las filas insertadas en MAPPALDB.
// Se reemplazan cuando existan los adapters de base de datos de cada vertical.
public final class DatosSemilla {

    private DatosSemilla() {}

    public static List<Turista> turistas() {
        List<Turista> lista = new ArrayList<>();
        lista.add(new Turista(1, "Ana", "Gomez", "1001234567", "3001112233", "ana@mail.com",
                LocalDate.of(1995, 4, 12), true, "Colombiana", "Español"));
        lista.add(new Turista(2, "Luis", "Perez", "1009876543", "3014445566", "luis@mail.com",
                LocalDate.of(1988, 9, 30), true, "Colombiana", "Español"));
        lista.add(new Turista(3, "Sofia", "Lopez", "1020304050", "3027778899", "sofia@mail.com",
                LocalDate.of(1999, 1, 15), false, "Colombiana", "Español"));
        return lista;
    }

    public static List<GuiaConductor> prestadores() {
        List<GuiaConductor> lista = new ArrayList<>();
        lista.add(new GuiaConductor(1, "Carlos", "Cordoba", "80111222", "3105551001", "carlos@guia.co",
                LocalDate.of(1985, 6, 20), true, 45000, true, 4.8));
        lista.add(new GuiaConductor(2, "Andres", "Duque", "80222333", "3105551002", "andres@guia.co",
                LocalDate.of(1990, 3, 8), true, 60000, false, 4.5));
        lista.add(new GuiaConductor(3, "Maria", "Ruiz", "1040506070", "3105551003", "maria@cond.co",
                LocalDate.of(1978, 11, 2), true, 35000, true, 4.9));
        return lista;
    }

    public static List<Itinerario> itinerarios() {
        List<Itinerario> lista = new ArrayList<>();
        lista.add(new Itinerario(1, "Guatape", LocalDateTime.of(2026, 12, 1, 0, 0),
                LocalDateTime.of(2026, 12, 3, 0, 0), "Cultural", NivelEstimulacion.BAJA));
        lista.add(new Itinerario(2, "Jardin", LocalDateTime.of(2026, 12, 10, 0, 0),
                LocalDateTime.of(2026, 12, 12, 0, 0), "Aventura", NivelEstimulacion.ALTA));
        lista.add(new Itinerario(3, "Cocora", LocalDateTime.of(2026, 12, 20, 0, 0),
                LocalDateTime.of(2026, 12, 21, 0, 0), "Naturaleza", NivelEstimulacion.MEDIA));
        return lista;
    }
}