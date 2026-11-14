# Convenciones de Equipo - MapPal
> Acuerdo del equipo del proyecto Integrador MapPal.
> Todo lo escrito aqui es obligatorio en cada Pull Request.

---

## 1. Estructura de paquetes

Raíz única `app.*`.

app                        → Main (solo arranca la app)
app.configuration          → Config (único lugar con new de implementaciones)
app.userinterface          → UserInterface (menús jerárquicos)
app.domain                 → entidades (datos puros)
app.domain.enums           → enumeraciones
app.service.inputport      → interfaces de entrada (XService)
app.service.outputport     → interfaces de salida (XRepository)
app.service                → implementaciones de lógica (XServiceImpl)
app.service.helpers        → utilidades del service
app.service.validations    → validadores de entrada
app.repository             → implementaciones de repositorio (XRepositoryImpl)
app.view                   → vistas (único lugar con Scanner y System.out)


---

## 2. Convención de nombres

| Elemento | Convención | Ejemplo |
|---|---|---|
| Paquetes | minúsculas, singular | `repository`, `service` |
| Interfaz de entrada | `XService` en `inputport` | `VehiculoService` |
| Interfaz de salida | `XRepository` en `outputport` (sin sufijo `Interface`) | `VehiculoRepository` |
| Implementación de service | `XServiceImpl` | `VehiculoServiceImpl` |
| Implementación de repo | `XRepositoryImpl` | `VehiculoRepositoryImpl` |
| Clases / enums | `PascalCase` | `EstadoReserva` |
| Atributos y métodos | `camelCase` | `registrarVehiculo()` |
| Constantes | `MAYUSCULAS_CON_GUION_BAJO` | `MAX_INTENTOS` |
| Idioma de identificadores | **Español** (coherente en todo el proyecto) | `buscarVehiculoPorId()` |

---

## 3. Convención de ramas

Base de trabajo: **`develop`**. Nunca se trabaja directo sobre `main`.


feature/00X/vertical-nombre   → feature/003/vertical-reserva
fix/00X/descripcion-corta     → fix/012/guardar-reserva-borraba-lista
refactor/00X/descripcion      → refactor/001/estructura_paquetes
docs/00X/descripcion          → docs/001/convenpciones

- Numeración correlativa de 3 dígitos (`001`, `002`, `003`...).

- **Una vertical = una rama = un Pull Request.**
- Merge a `develop` solo desde un PR aprobado.

---

## 4. Reglas de Pull Request

1. **Quien NO hizo el cambio, lo revisa** (revisión cruzada obligatoria).
2. Se requiere **1 aprobación** para hacer merge.
3. Mensaje de commit con prefijo según tipo: `feat:`, `fix:`, `refactor:`, `docs:`.
4. **Nunca** se suben `.idea/`, `target/` ni archivos IDE al repositorio.
5. Un PR debe compilar y probarse antes de pedir revisión.

---

## 5. Reglas de arquitectura (verificadas en cada PR)

1. `System.out` y `Scanner` **solo** existen en `app.view`.
2. `new` de implementaciones (`XRepositoryImpl`, `XServiceImpl`) **solo** en `app.configuration.Config`.
3. Los services reciben sus dependencias **por constructor** con campo `final`.
4. Las lecturas de puertos devuelven **`Optional<T>`**, nunca `null`.
5. Las entidades de `app.domain` son **solo datos** (atributos, constructores, getters/setters).
6. La lógica de negocio vive en `XServiceImpl`, **nunca** en la view ni en el repositorio.

---

## 6. Definición de hecho (Checklist por PR)

- [ ] Compila sin errores.
- [ ] Flujo probado en consola: registrar, listar, buscar, actualizar, eliminar.
- [ ] Sin `System.out` ni `Scanner` fuera de `app.view`.
- [ ] Sin `new` de implementaciones fuera de `app.configuration`.
- [ ] Sin `null` en lecturas de puertos (se usa `Optional<T>`).
- [ ] Sin imports sin usar.
- [ ] PR revisado y aprobado por un compañero distinto al autor.

---

## 7. Registro de decisiones (ADR)

Las decisiones arquitectónicas relevantes se documentan en este mismo archivo,
agregando una sección con: **fecha, decisión, alternativas consideradas y motivo.**

| Fecha | Decisión | Motivo |
|---|---|---|
| 2026-10-19 | Raíz de paquetes `app.*` | `Main` ya vive en `app`; evita colisiones de nombres |
| 2026-10-19 | Interfaz de salida se llama `XRepository` | Un solo estilo; ShortHomeApp mezclaba dos |
| 2026-10-19 | Identificadores en español | Coherencia con el código existente; costo de cambio cero |

