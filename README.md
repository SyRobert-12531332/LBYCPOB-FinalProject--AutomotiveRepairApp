PROJECT TITLE: AutoTrack: Vehicle Repair Management System
AutomotiveRepairApp

TEAM MEMBERS:

<Gabito, Nath Nevan N.> - sben-nnn

<Jagonap, Angelo Mikhail M.> - hailm1k

<Sy, Robert Vinzon C.> - SyRobert-12531332


PROBLEM STATEMENT & GOALS:
<What problem does this solve? What are the main objectives?>
Auto repair shops often track vehicle intake, damage reports, and repair progress manually using paper logs or scattered notes. This makes it hard to monitor which vehicles are pending, in progress, or completed, and repair history gets lost or is hard to reference later. The system aims to centralize vehicle repair records in one place, let mechanics log damage and update progress digitally, and give supervisors a clear view of shop status at any time.

TARGET USER:
Mechanics and supervisors working in an auto repair shop.

BRIEF DESCRIPTION:
AutoTrack is a system that manages the full repair workflow of a vehicle from intake to completion. Users log in as either a mechanic or supervisor, register vehicles, log damage found during inspection, view repair instructions for specific parts, and update repair progress. Supervisors and mechanics can also check repair history and pending repairs through a shared dashboard.

CORE OOP CONCEPTS:
-Encapsulation: Vehicle and RepairLog classes keep their fields (owner name, plate number, damaged part, notes) private, exposed only through getters and setters, so data can't be changed directly from outside the class.
-Inheritance: A base User class holds shared login and identification behavior, with Mechanic and Supervisor as subclasses that extend it with role-specific actions.
-Polymorphism: Mechanic and Supervisor override a method like getMenuOptions() or generateView() differently, so each role sees a different set of dashboard actions when the same method is called.
-Abstraction: An abstract RepairPart or Repairable class defines what every part needs (getInstructions(), getTools()) without exposing how each part's repair steps are implemented internally.

INITIAL CLASS IDEAS:
Model Classes
Person: An abstract or base class representing any individual interacting with or managed by the system.
User: Represents a registered system account capable of logging into the desktop application.
Mechanic: A specialized class (likely inheriting from Person or linked to a User profile) representing the shop technicians responsible for performing maintenance operations and fulfilling repair instructions.
Vehicle: The base class for all items brought into the shop for servicing. It defines core attributes and methods shared by all motorized transport types.
Motorcycle: A specific subclass of Vehicle representing two- or three-wheeled motor vehicles, capturing unique traits such as engine displacement type or chain maintenance needs.
Sedan: A specific subclass of Vehicle representing standard four-door passenger cars.
SUV: A specific subclass of Vehicle representing Sport Utility Vehicles, potentially adding attributes for four-wheel-drive characteristics or larger cargo/passenger capacities.
Pickup: A specific subclass of Vehicle representing light-duty trucks equipped with an open cargo bed, tracking specific details like bed length or towing capacity.
Van: A specific subclass of Vehicle representing multi-purpose passenger or commercial transport vans.
Repair: Represents an overarching service job or ticket for a specific vehicle brought into the shop.
RepairInstruction: Represents a discrete task or step-by-step procedure required to complete a larger Repair job. This breaks down a major service into actionable items for mechanics.

Service Classes
LoginService: Validates user credentials and securely establishes the identity and access permissions of the logged-in mechanic.
VehicleService: Coordinates the business rules for managing vehicles, ensuring proper formatting, ownership assignment, and fleet integrity.
RepairService: Manages the life cycle and scheduling workflows of repair tasks, tracking urgency weights, mechanic assignments, and completion progress.
InstructionService: Handles the logic for retrieving, updating, and associating specific standard operating procedures (SOPs) with automotive parts.

Repository Classes
The repository layer acts as an abstraction over your data storage, handling all direct data access, retrieval, and persistence operations.
UserRepository: Handles direct read and write operations for user profile records within the underlying persistent storage.
VehicleRepository: Manages the low-level data storage, querying, and deletion operations for registered vehicle assets.
RepairRepository: Responsible for saving, loading, and updating active or completed repair logs directly to and from the data source.

Util Classes
Utilities are independent, reusable helper functions that provide common technical capabilities across the entire application.
JsonUtil: Provides global helper methods to serialize Java objects into JSON strings and parse JSON strings back into structured data.
ValidationUtil: Offers centralized checks to ensure inputs (like email formats, phone numbers, or vehicle plate syntax) conform to required patterns before processing.

CORE FEATURES (Recommended):
-Vehicle registration and search by plate number or owner name
-Damage logging by part with status classification (Needs Repair, Urgent, Minor)
-Repair progress updates with automatic mechanic/date/time tracking
-Repair history and pending repairs viewing
-Role-based login distinguishing mechanic and supervisor access

Theoretical Structure

AutomotiveRepairSystem/
│
├── pom.xml
│
└── src
    └── main
        ├── java
        │   └── com
        │       └── autoworks
        │           ├── App.java
        │           ├── model
        │           │   ├── Person.java
        │           │   ├── User.java
        │           │   ├── Mechanic.java
        │           │   ├── Vehicle.java
        │           │   ├── Sedan.java
        │           │   ├── SUV.java
        │           │   ├── Van.java
        │           │   ├── Pickup.java
        │           │   ├── Motorcycle.java
        │           │   ├── Repair.java
        │           │   └── RepairInstruction.java
        │           ├── service
        │           │   ├── LoginService.java
        │           │   ├── VehicleService.java
        │           │   ├── RepairService.java
        │           │   └── InstructionService.java
        │           ├── repository
        │           │   ├── UserRepository.java
        │           │   ├── VehicleRepository.java
        │           │   └── RepairRepository.java
        │           ├── ui
        │           │   ├── LoginFrame.java
        │           │   ├── DashboardFrame.java
        │           │   ├── VehicleFrame.java
        │           │   ├── RepairFrame.java
        │           │   └── InstructionFrame.java
        │           └── util
        │               ├── JsonUtil.java
        │               └── ValidationUtil.java
        │
        └── resources
            ├── styles.css
            ├── users.json
            ├── vehicles.json
            ├── repairs.json
            └── instructions.json
