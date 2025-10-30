# 🚀 Proyecto: Simulación de Sistema de Restaurante (Rol Cajero)

Este proyecto es una implementación en Java de un sistema de punto de venta (POS), enfocado específicamente en las funcionalidades del actor **Cajero**.

No es solo un ejercicio de código, sino la materialización de un conjunto de artefactos de ingeniería de software, incluyendo:

* [cite_start]**Tarjetas CRC** (Clase-Responsabilidad-Colaborador) [cite: 1]
* [cite_start]**Casos de Uso** detallados [cite: 9]
* Un **Modelo de Dominio**

## 📋 Contexto y Características Implementadas

El sistema simula el flujo de trabajo de un cajero en un restaurante, basado en los requerimientos de los documentos provistos.

### Casos de Uso Principales

* **CA1. [cite_start]Procesar Pago**[cite: 19]: Es el flujo central. [cite_start]El sistema permite al cajero registrar el pago de una transacción activa[cite: 19, 20].
    * [cite_start]El cajero consulta la forma de pago (efectivo/tarjeta)[cite: 28, 29].
    * [cite_start]El sistema procesa el pago y calcula el vuelto si es efectivo[cite: 31].
    * [cite_start]El sistema actualiza el registro de caja[cite: 41].
    * [cite_start]Se descuenta el stock del inventario[cite: 41].

* **CA1a. [cite_start]Validar Forma de Pago**[cite: 42]:
    * [cite_start]**Tarjeta:** El sistema se comunica con una `EntidadFinanciera` externa (simulada) para autorizar o rechazar el pago[cite: 3, 31, 42].
    * [cite_start]**Efectivo:** El sistema valida que el monto recibido sea suficiente[cite: 42].

* **CA1b. [cite_start]Emitir Comprobante**[cite: 43]:
    * [cite_start]Una vez que el pago es aprobado y la transacción se completa [cite: 43][cite_start], el sistema genera e imprime (en consola) un comprobante[cite: 33, 43].

* **CA1c. [cite_start]Anular Pago**[cite: 44]:
    * [cite_start]El cajero puede anular una transacción antes de que se finalice[cite: 40, 44].
    * [cite_start]El sistema solicita confirmación y revierte la transacción a su estado pendiente[cite: 41, 44].

### Flujos Alternativos

* [cite_start]**Pago Rechazado** [cite: 35][cite_start]: Si la entidad financiera rechaza la transacción (ej. fondos insuficientes), el sistema informa al cajero, permitiéndole solicitar otro método de pago[cite: 36, 37, 38, 39].

## 📂 Estructura del Proyecto

El código está organizado en una arquitectura limpia siguiendo el Modelo de Dominio:

* **`/modelo`**: Contiene las clases del dominio (los "sustantivos").
    * `Transaccion`, `Producto`, `DetalleTransaccion`, `Caja`, `Comprobante`.
    * `Pago` (clase abstracta) y sus clases hijas `Efectivo` y `Tarjeta`, implementando la herencia del diagrama.
    * `EstadoTransaccion` (Enum).

* **`/servicio`**: Contiene la lógica de negocio (los "verbos").
    * [cite_start]`ControladorCaja`: Representa al "Sistema" de las tarjetas CRC[cite: 3]. Orquesta los casos de uso, se comunica con los modelos y los sistemas externos.

* **`/simulacion`**: Simula los colaboradores externos.
    * [cite_start]`EntidadFinanciera`[cite: 6]: Simula la respuesta de aprobación o rechazo de un banco.
    * [cite_start]`Impresora`[cite: 3]: Simula el hardware de impresión enviando el texto a la consola.

* **`Main.java`**: Punto de entrada de la aplicación. [cite_start]Simula al actor **Cajero** [cite: 12] interactuando con el sistema para procesar varias transacciones de prueba.

## 💻 Cómo Ejecutar

Este es un proyecto Java estándar sin dependencias externas.

1.  Clona o descarga el repositorio.
2.  Abre el proyecto en tu IDE favorito (IntelliJ, Eclipse, VS Code).
3.  Ejecuta el archivo `Main.java`.
4.  Toda la simulación (flujo de pagos, autorizaciones bancarias y comprobantes impresos) se mostrará en la consola.
