# 🚀 Proyecto: Simulación de Sistema de Caja (Rol Cajero)

Este proyecto es una implementación en Java de un sistema de punto de venta (POS), enfocado específicamente en las funcionalidades del actor **Cajero**.

No es solo un ejercicio de código, sino la materialización de un conjunto de artefactos de ingeniería de software, incluyendo:

* **Tarjetas CRC** (Clase-Responsabilidad-Colaborador)
* **Casos de Uso** detallados
* Un **Modelo de Dominio**

## 📋 Contexto y Características Implementadas

El sistema simula el flujo de trabajo de un cajero en un restaurante, basado en los requerimientos de los documentos provistos.

### Casos de Uso Principales

* **CA1. Procesar Pago**: Es el flujo central. El sistema permite al cajero registrar el pago de una transacción activa.
    * El cajero consulta la forma de pago (efectivo/tarjeta).
    * El sistema procesa el pago y calcula el vuelto si es efectivo.
    * El sistema actualiza el registro de caja.
    * Se descuenta el stock del inventario.

* **CA1a. Validar Forma de Pago**:
    * **Tarjeta:** El sistema se comunica con una `EntidadFinanciera` externa (simulada) para autorizar o rechazar el pago.
    * **Efectivo:** El sistema valida que el monto recibido sea suficiente.

* **CA1b. Emitir Comprobante**:
    * Una vez que el pago es aprobado y la transacción se completa, el sistema genera e imprime (en consola) un comprobante.

* **CA1c. Anular Pago**:
    * El cajero puede anular una transacción antes de que se finalice.
    * El sistema solicita confirmación y revierte la transacción a su estado pendiente.

### Flujos Alternativos

* **Pago Rechazado**: Si la entidad financiera rechaza la transacción (ej. fondos insuficientes), el sistema informa al cajero, permitiéndole solicitar otro método de pago.

## 📂 Estructura del Proyecto

El código está organizado en una arquitectura limpia siguiendo el Modelo de Dominio:

* **`/modelo`**: Contiene las clases del dominio (los "sustantivos").
    * `Transaccion`, `Producto`, `DetalleTransaccion`, `Caja`, `Comprobante`.
    * `Pago` (clase abstracta) y sus clases hijas `Efectivo` y `Tarjeta`, implementando la herencia del diagrama.
    * `EstadoTransaccion` (Enum).

* **`/servicio`**: Contiene la lógica de negocio (los "verbos").
    * `ControladorCaja`: Representa al "Sistema" de las tarjetas CRC. Orquesta los casos de uso, se comunica con los modelos y los sistemas externos.

* **`/simulacion`**: Simula los colaboradores externos.
    * `EntidadFinanciera`: Simula la respuesta de aprobación o rechazo de un banco.
    * `Impresora`: Simula el hardware de impresión enviando el texto a la consola.

* **`Main.java`**: Punto de entrada de la aplicación. Simula al actor **Cajero** interactuando con el sistema para procesar varias transacciones de prueba.

## 💻 Cómo Ejecutar

Este es un proyecto Java estándar sin dependencias externas.

1.  Clona o descarga el repositorio.
2.  Abre el proyecto en tu IDE favorito (IntelliJ, Eclipse, VS Code).
3.  Ejecuta el archivo `Main.java`.
4.  Toda la simulación (flujo de pagos, autorizaciones bancarias y comprobantes impresos) se mostrará en la consola.
