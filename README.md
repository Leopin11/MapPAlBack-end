
Plataforma web de turismo universal con comercio justo y accesibilidad integral.

---

## 📋 Tabla de contenido

- [Resumen ejecutivo](#resumen-ejecutivo)
- [Objetivos principales](#objetivos-principales)
- [Resultados esperados](#resultados-esperados)
- [Introducción](#introducción)
- [Propósito del software](#propósito-del-software)
- [Público objetivo](#público-objetivo)
- [Impacto esperado](#impacto-esperado)
- [Problemas a resolver](#problemas-a-resolver)
- [Solución propuesta](#solución-propuesta)
- [Beneficios](#beneficios)
- [Innovación](#innovación)
- [Tecnologías a utilizar](#tecnologías-a-utilizar)
- [Diseño del sistema](#diseño-del-sistema)
- [Historias de usuario](#historias-de-usuario)
- [Diagramas del proyecto](#diagramas-del-proyecto)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)
- [Contacto](#contacto)

---

## 📄 Resumen ejecutivo

### Breve descripción del proyecto

MapPal es una plataforma web de turismo universal que conecta de forma directa a viajeros de todo tipo con guías, comercios y conductores locales independientes, eliminando intermediarios tradicionales para garantizar un comercio justo. El proyecto incorpora, además, un ecosistema de accesibilidad pionero que permite a cualquier turista filtrar experiencias según sus necesidades físicas, sensoriales, cognitivas o de comunicación.

### Objetivos principales

- Conectar turistas con guías, conductores y comercios locales sin intermediarios, garantizando que el pago llegue directamente al trabajador o negocio local.
- Habilitar a comercios locales (gastronomía, artesanías y servicios) para ofrecer sus productos o experiencias directamente a los turistas, bajo el mismo modelo de pago directo que los guías y conductores.
- Ofrecer un motor de filtros de accesibilidad que cruce en tiempo real los requerimientos del turista con las características verificadas de vehículos, rutas, guías y comercios.
- Empoderar a los guías, conductores y comercios independientes mediante un perfil avanzado que reconozca su especialización con una insignia de "Servicio Especializado".
- Construir una arquitectura full-stack (Java Spring Boot, SQL Server y HTML/CSS/JavaScript) que soporte el algoritmo de emparejamiento (matchmaking) entre turistas y prestadores de servicio.

### Resultados esperados

Se espera obtener un producto funcional que permita registrar turistas, guías, conductores, comercios y vehículos; configurar y aplicar filtros de accesibilidad sobre la oferta disponible; y generar reservas con pago directo al prestador local. El resultado documenta además el diseño del sistema (historias de usuario y diagrama de clases) como base para las siguientes fases de desarrollo.

---

## 🏁 Introducción

### Contexto del proyecto

El turismo tradicional suele operar a través de agencias y plataformas que intermedian entre el viajero y el prestador local del servicio, reteniendo una parte importante del valor generado y ofreciendo, en la mayoría de los casos, itinerarios rígidos que no contemplan necesidades particulares de accesibilidad. MapPal surge como respuesta a esa doble limitación: económica, porque el guía o conductor local recibe una fracción menor de lo que paga el turista, y de accesibilidad, porque los turistas con requerimientos físicos, sensoriales, cognitivos o de comunicación encuentran pocas opciones adaptadas a su realidad.

### Justificación

Un modelo de comercio justo que dirija el pago directamente al trabajador o negocio local dinamiza la economía de barrio y dignifica el oficio de guías, conductores y comerciantes independientes. Al mismo tiempo, incorporar un diseño verdaderamente universal —no como un módulo adicional, sino como un motor de filtros central— amplía el mercado potencial de la plataforma a un segmento de viajeros históricamente desatendido por el sector turístico.

### Alcance general

Para esta primera fase del proyecto integrador, el alcance se centra en el diseño de los requerimientos, las historias de usuario y el diagrama de clases UML que soportarán el desarrollo posterior del sistema: registro y perfilado de turistas y guías/conductores, catálogo de etiquetas de accesibilidad, gestión de vehículos y generación de reservas con base en el emparejamiento entre la oferta y los filtros configurados por el turista.

---

## 🎯 Propósito del software

### Objetivo del desarrollo

Desarrollar una plataforma web que automatice el emparejamiento entre turistas y guías/conductores locales a partir de criterios de destino, tipo de experiencia y, de forma diferencial, requerimientos de accesibilidad, sustituyendo la intermediación tradicional por una transacción directa entre las partes.

### Público objetivo

- Turistas nacionales y extranjeros que buscan experiencias auténticas y a la medida.
- Turistas con discapacidad física, sensorial o cognitiva, y viajeros neurodivergentes, que hoy encuentran poca oferta adaptada.
- Guías turísticos y conductores independientes que desean ofrecer sus servicios sin depender de agencias intermediarias.
- Comercios locales (restaurantes, talleres artesanales y negocios de servicios) que desean vender directamente a los turistas sin ceder margen a intermediarios.

### Impacto esperado

Se espera aumentar el ingreso directo percibido por los trabajadores turísticos locales, ampliar la oferta accesible del sector y posicionar a MapPal como un caso de aplicación de diseño universal dentro del turismo digital.

---

## ❗ Problemas a resolver

### Situación actual

Las plataformas de turismo convencionales concentran la intermediación del pago, ofrecen paquetes estandarizados y rara vez permiten configurar requerimientos de accesibilidad más allá de menciones genéricas en la descripción del servicio. Esto deja al turista con necesidades específicas en la tarea manual de contactar y verificar, uno por uno, si un guía o conductor puede atenderlo adecuadamente.

### Necesidades detectadas

- Un canal de pago directo entre turista y prestador del servicio, sin comisiones de intermediarios tradicionales.
- Un mecanismo estructurado y verificable para declarar y consultar condiciones de accesibilidad física, sensorial, cognitiva y de comunicación.
- Un perfil enriquecido para guías, conductores y comercios que les permita demostrar competencias o adaptaciones especiales y ser encontrados por quienes las requieren.
- Una base de datos relacional que sostenga de forma consistente la relación entre personas, vehículos y etiquetas de accesibilidad.

---

## 💡 Solución propuesta

### Descripción de la solución

MapPal resuelve estas necesidades mediante tres componentes que trabajan de forma integrada:

1. **Un motor de reserva universal** que arma itinerarios a la medida y empareja directamente al turista con el prestador local —guía, conductor o comercio—.
2. **Un sistema de filtros inclusivos** que aplica el principio de diseño universal sobre la búsqueda estándar.
3. **Un perfilado avanzado** para guías, conductores y comercios que reconoce y prioriza su especialización mediante una insignia de "Servicio Especializado".

### Beneficios

- El pago va directo al guía, conductor o comercio, sin comisiones de intermediación tradicional.
- El turista solo ve opciones compatibles con sus requerimientos reales de accesibilidad.
- El prestador local que invierte en adaptar su servicio o su local puede cobrar tarifas justas por ese valor agregado y ganar visibilidad prioritaria.

### Innovación

La innovación central no es el modelo de comercio directo en sí —ya existente en otras plataformas colaborativas— sino su combinación con un motor de accesibilidad que cruza en tiempo real etiquetas inclusivas de tres categorías (física, sensorial/cognitiva y comunicación) contra la disponibilidad real de vehículos, rutas y guías, algo poco común en el sector turístico digital.

---

## 🛠️ Tecnologías a utilizar

### Lenguajes de programación

- **Java** — lógica de negocio y algoritmo de emparejamiento (matchmaking) en el backend.
- **SQL** — definición y consulta del modelo de datos relacional.
- **HTML, CSS y JavaScript** — construcción de la interfaz de usuario.

### Frameworks y herramientas

- **Spring Boot** — exposición de servicios y controladores REST del backend.
- **SQL Server** — motor de base de datos relacional para las tablas Turistas, Guias_Conductores, Comercios, Vehiculos y Etiquetas_Inclusivas.
- **Git / GitHub** — control de versiones para el seguimiento del código fuente.

---

## 🏗️ Diseño del sistema

El diseño del sistema se apoya en un modelo orientado a objetos con una clase abstracta **Persona** de la que heredan **Turista** y **GuiaConductor**, siguiendo el mismo patrón usado en la plantilla del proyecto integrador. **Comercio**, en cambio, no hereda de Persona porque representa un negocio y no un individuo: registra nombre, tipo de comercio, dirección y horario en lugar de datos personales.

Como **GuiaConductor** y **Comercio** cumplen el mismo rol frente al turista —ofrecer un servicio y recibir pago directo—, ambos implementan una interfaz común **PrestadorLocal**, que declara ese comportamiento compartido (tarifa base, insignia de servicio especializado, registro de certificados) sin obligar a Comercio a heredar atributos de persona que no le corresponden.

Alrededor de estas clases se organizan las de apoyo que sostienen el motor de accesibilidad (**EtiquetaInclusiva**, **CategoriaEtiqueta**), la oferta de servicio (**Vehiculo**, **Certificado**, **TipoComercio**) y la transacción (**Itinerario**, **Reserva**, **EstadoReserva**).

---

## 📚 Historias de usuario

### HU-01 — Persona (clase abstracta)

**Como:** Coordinador de la plataforma  
**Quiero:** registrar los datos básicos de identidad de cualquier persona vinculada al sistema: identificador, nombre, apellido, fecha de nacimiento, documento de identidad, teléfono, correo y si está activa  
**Para:** tener una base confiable y centralizada de todas las personas del sistema, sin importar si actúan como turistas o como guías/conductores

**Criterios de aceptación:**
- Toda persona registrada debe tener un identificador único y obligatorio.
- El sistema debe calcular automáticamente la edad de la persona a partir de su fecha de nacimiento.
- El sistema debe mostrar un resumen legible de los datos de cualquier persona, sin importar si es turista o guía/conductor.

### HU-02 — Registro de turista

**Como:** Turista  
**Quiero:** registrarme en la plataforma indicando mis datos personales, mi nacionalidad y mi idioma preferido  
**Para:** poder armar itinerarios y realizar reservas con guías y conductores locales

**Criterios de aceptación:**
- El sistema debe validar que el documento de identidad no esté duplicado.
- El turista debe quedar asociado, desde el registro, a la clase Persona con sus datos básicos.
- El sistema debe permitir editar el idioma preferido y la nacionalidad después del registro inicial.

### HU-03 — Registro de guía o conductor

**Como:** Guía o conductor local independiente  
**Quiero:** registrarme indicando mi tarifa base y, si aplica, mis vehículos y certificados de habilidades especiales  
**Para:** ofrecer mis servicios directamente a los turistas y recibir el pago sin intermediarios

**Criterios de aceptación:**
- Todo guía o conductor debe registrar una tarifa base obligatoria.
- El sistema debe permitir asociar cero o varios vehículos y cero o varios certificados a un mismo guía/conductor.
- El resumen de datos de un guía/conductor debe mostrar, además de sus datos de persona, su calificación promedio y si cuenta con insignia de servicio especializado.

### HU-04 — Registro de comercio local

**Como:** Comerciante local (dueño de un restaurante, taller artesanal u otro negocio)  
**Quiero:** registrar mi comercio con su nombre, tipo de comercio (gastronomía, artesanías, servicios u otro), dirección, horario de atención y tarifa base  
**Para:** ofrecer mis productos o servicios directamente a los turistas y recibir el pago sin intermediarios, en igualdad de condiciones que un guía o conductor

**Criterios de aceptación:**
- Todo comercio debe registrar un nombre, un tipo de comercio y una tarifa base obligatorios.
- El sistema debe permitir asociar al comercio una o varias etiquetas inclusivas que describan la accesibilidad física de su local.
- El comercio debe implementar el mismo contrato PrestadorLocal que un guía o conductor, de modo que pueda recibir reservas y optar a la insignia de servicio especializado con las mismas reglas.

### HU-05 — Registro de vehículo

**Como:** Conductor local  
**Quiero:** registrar las especificaciones técnicas reales de mi vehículo, incluyendo si tiene capacidad de baúl para silla de ruedas y si permite rutas sin escalones  
**Para:** que la plataforma pueda ofrecerlo automáticamente a turistas con requerimientos de accesibilidad física compatibles

**Criterios de aceptación:**
- El vehículo debe registrar placa, capacidad de pasajeros y los indicadores de accesibilidad física.
- Un vehículo debe poder asociarse a una o varias etiquetas inclusivas.
- El sistema debe impedir registrar dos vehículos con la misma placa.

### HU-06 — Configuración de filtros de accesibilidad

**Como:** Turista con requerimientos específicos de accesibilidad  
**Quiero:** seleccionar, antes de buscar, las etiquetas inclusivas que necesito (físicas, sensoriales/cognitivas o de comunicación)  
**Para:** que el sistema descarte automáticamente las opciones que no son compatibles con mis necesidades

**Criterios de aceptación:**
- El turista debe poder seleccionar una o varias etiquetas inclusivas de al menos las tres categorías definidas.
- El sistema debe excluir de los resultados de búsqueda a los guías, conductores o vehículos que no cumplan todas las etiquetas seleccionadas como obligatorias.
- El sistema debe mostrar, junto a cada resultado, qué etiquetas inclusivas cumple.

### HU-07 — Insignia de servicio especializado

**Como:** Guía o conductor que ha invertido en adaptaciones o capacitación  
**Quiero:** que la plataforma reconozca automáticamente mi especialización con una insignia visible  
**Para:** recibir prioridad en las búsquedas inclusivas y poder cobrar una tarifa justa por mi valor agregado

**Criterios de aceptación:**
- El sistema debe otorgar la insignia de "Servicio Especializado" cuando el guía/conductor tenga al menos un certificado válido o un vehículo con dos o más etiquetas de accesibilidad física.
- Los perfiles con insignia deben aparecer priorizados en los resultados de búsquedas inclusivas.
- La insignia debe ser visible en el resumen público del perfil del guía/conductor.

### HU-08 — Reserva y pago directo

**Como:** Turista  
**Quiero:** reservar un itinerario con un guía, conductor o comercio local específico y pagar directamente por la plataforma  
**Para:** que el dinero llegue sin intermediarios al prestador local que presta el servicio

**Criterios de aceptación:**
- Toda reserva debe quedar asociada a un turista, un prestador local (guía, conductor o comercio) y un itinerario.
- El sistema debe registrar el estado de la reserva (pendiente, confirmada, completada o cancelada) y sus cambios.
- El monto pagado debe reflejarse como ingreso directo del prestador local, sin comisión de intermediación.

---

## 📊 Diagramas del proyecto

### Diagrama de clases UML

El siguiente diagrama de clases representa el modelo de dominio de MapPal. **Persona** es una clase abstracta de la que heredan **Turista** y **GuiaConductor**. **GuiaConductor** y **Comercio** implementan la interfaz **PrestadorLocal**, que concentra el comportamiento común a ambos (tarifa base, insignia de servicio especializado, registro de certificados) sin forzar a Comercio a heredar atributos de persona.

Un **GuiaConductor** puede poseer varios **Vehiculo**, y tanto **GuiaConductor** como **Comercio** pueden acreditar varios **Certificado**. **Turista**, **GuiaConductor**, **Comercio** y **Vehiculo** se relacionan con **EtiquetaInclusiva** para expresar, respectivamente, requerimientos, especialidades y atributos de accesibilidad; **Comercio** además clasifica su actividad mediante **TipoComercio**. Cada **Reserva** vincula a un **Turista** con un **PrestadorLocal** (guía, conductor o comercio) a través de un **Itinerario**, y mantiene su propio estado (**EstadoReserva**).

> 📎 Consulta el diagrama de clases UML en la documentación del proyecto.

---

## 🤝 Contribuciones

MapPal es un proyecto del equipo integrador. Para contribuir:

1. Crea una rama desde `main` o `develop`.
2. Desarrolla tu funcionalidad siguiendo las convenciones del equipo.
3. Abre un Pull Request describiendo los cambios realizados.

---

## 📄 Licencia

Este proyecto es de uso académico/interno del equipo MapPal.

---

## 📬 Contacto

- **Repositorio GitHub:** https://github.com/Leopin11/MapPAlBack-end
- **Equipo MapPal**
1. Leonardo Pinto.
2. Carlos Cordoba.
3. Andres Duque.
---
