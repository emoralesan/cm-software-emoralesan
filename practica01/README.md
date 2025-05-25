Práctica No. 1 - Refactorización con Principios de Construcción que forma parte del Proyecto de Construcción

A) Explicación de lo que hacen las clases GildedRose e Item

Clase Item
La clase Item representa un objeto que tiene tres atributos principales:
• name: el nombre del artículo.
• sellIn: los días que quedan para vender el artículo.
• quality: la calidad actual del artículo.
Esta clase tiene un constructor para inicializar estos atributos y un método toString() para representar el artículo como una cadena de texto con sus tres propiedades separadas por comas. La clase básicamente modela un artículo o ítem que puede ser gestionado en la tienda.

Clase GildedRose
La clase GildedRose administra un conjunto de objetos tipo Item. Contiene:
• Un arreglo de objetos Item[] items.
• Un método llamado updateQuality() que actualiza diariamente el atributo quality y sellIn de cada artículo según reglas específicas.
El método updateQuality() se encarga de modificar la calidad y el tiempo restante para vender el ítem, aplicando diferentes condiciones según el tipo de artículo

B) Malas prácticas encontradas

A continuación, se detallan malas prácticas encontradas en las clases Item y GildedRose, con base en los principios de Modularidad, Separación de responsabilidades, DRY, KISS, cohesión, desacoplamiento, legibilidad, mantenibilidad, reutilización de componentes y SOLID, según lo visto en clase:

Clase Item

1. Violación del principio de encapsulamiento (SOLID - SRP y encapsulación):
Los atributos name, sellIn y quality son públicos, lo que rompe el encapsulamiento. Esto expone directamente el estado interno del objeto y dificulta controlar las modificaciones y validaciones necesarias.

2. Falta de métodos para manipular el estado:
La clase solo tiene un constructor y un método toString(), no encapsula la lógica de actualización del estado ni la validación de atributos, lo que podría generar que esta responsabilidad recaiga en otras clases (aumentando el acoplamiento).

3. Baja cohesión:
Aunque la clase representa un ítem, no maneja comportamientos relacionados (como actualizar su calidad o días para venta), por lo que su funcionalidad es muy limitada y delega todas las responsabilidades a otras clases, pero sin un mecanismo adecuado para mantener bajo acoplamiento.

Clase GildedRose

1. Complejidad y anidamiento elevado (KISS):
El método updateQuality() tiene demasiados niveles de anidación, condicionales complejas y repeticiones, dificultando la legibilidad y comprensión. Se rompe el principio KISS (Keep It Simple, Stupid). Debería ser más simple y modular.

2. Violación del principio DRY (Don't Repeat Yourself):
Hay código repetido en varios lugares, por ejemplo, chequeos múltiples de items[i].quality < 50 y modificaciones repetidas de quality o sellIn. Fragmentos muy similares se repiten para distintos casos, lo que puede llevar a errores o inconsistencias.

3. Acoplamiento fuerte y baja cohesión:
La clase GildedRose centraliza toda la lógica de negocios para múltiples tipos de items, lo que aumenta su responsabilidad y disminuye la cohesión. Además, depende directamente de estructuras internas de Item (acceso a campos públicos), lo que genera acoplamiento fuerte.

4. Falta de modularidad y separación de responsabilidades (SOLID - SRP):
Toda la lógica de actualización de calidad y días para venta está concentrada en un único método largo. Debería delegar la lógica específica de cada tipo de item a clases o métodos separados.

5. Uso de cadenas literales para condicionales ("hardcoded strings"):
Los nombres de los items están hardcodeados en múltiples puntos ("Aged Brie", "Sulfuras, Hand of Ragnaros", etc.), lo que afecta la mantenibilidad y propenso a errores tipográficos. Se viola el principio abierto/cerrado (SOLID) porque modificar los nombres o agregar nuevos items obliga a cambiar esta clase.

6. Poco desacoplamiento:
No se utilizan interfaces o patrones para abstraer la lógica específica de cada tipo de artículo. No hay reutilización de componentes, porque toda la lógica está en esta misma clase.

7. Legibilidad y mantenibilidad pobre:
El código es difícil de seguir y mantener debido a la complejidad y falta de estructuras claras. No hay documentación ni comentarios que expliquen las reglas del negocio ni la intención del código.


C) Refactorización
Para adaptar las clases Item y GildedRose y aplicar principios de calidad de código (Modularidad, Responsabilidad única (SRP), DRY, KISS, cohesión, desacoplamiento, legibilidad, mantenibilidad, reutilización y SOLID), así como aplicar explícitamente el patrón de comportamiento Strategy (según lo visto en clase), se propone:

1. Modularidad y Separación de Responsabilidades (SRP)
•	Item solo debe representar los datos de un ítem (nombre, sellIn, quality), con encapsulamiento.
•	La lógica para actualizar sellIn y quality debe estar en clases independientes para cada tipo de ítem.
•	La clase GildedRose solo debe orquestar el proceso y delegar la actualización según la estrategia de cada ítem.

2. DRY y KISS
•	Evitamos código repetido creando clases concretas que implementen la interfaz UpdateStrategy.
•	Simplificamos verificaciones y condiciones moviéndolas a las estrategias específicas.

3. Cohesión y desacoplamiento
•	Cada estrategia es cohesiva: implementa solo la actualización de calidad y sellIn para su tipo de ítem.
•	GildedRose desconoce la lógica interna, solo elige la estrategia adecuada.

4. Legibilidad y mantenibilidad
•	El código es limpio, con métodos cortos y claros.
•	El uso de clases separadas facilita pruebas unitarias independientes.

5. Reutilización y SOLID
•	Se respeta SRP y OCP (Open/Close -> abiertas para su extensión, pero cerradas para su modificación): para añadir nuevos tipos de ítems no modificamos GildedRose.
•	Polimorfismo garantiza apertura a extensión y cierre a modificación.
