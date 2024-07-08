### M183 Abschlussprojekt von Kai Wenninger, Jack Siemers und Noah Lauper

Dieses Projekt implementiert Sicherheitsfunktionen für eine Webanwendung mit Spring Security. Folgende Themen werden behandelt:

### 1. Aktuelle Bedrohungen erkennen
- **OWASP Top Ten**: Regelmässige Aktualisierung kritischer Sicherheitsrisiken. Unser Thema ist A07 Identification and Authentication Failures.
- **Gegenmassnahmen**: Vorbereitete Statements, MFA, HTTPS.

### 2. Sicherheitslücken erkennen und beheben
- **Sicherheitstests**: Statische Codeanalyse, Penetration Testing, automatisierte Scans.
- **Gegenmassnahmen**: Input Validierung, sichere Konfigurationen, regelmässige Updates.

### 3. Authentifizierung und Autorisierung
- **Authentifizierung**: Die Authentifizierung erfolgt in unserem Projekt mit einem Json Web Token (JWT) und das Projekt wurde auch mit Postman getestet.
- **Autorisierung**: Rollen und Berechtigungen (@PreAuthorize, @Secured).

### 4. Sicherheitsaspekte bei Entwurf und Implementierung
- **Architektur**: Layered Security, Least Privilege, Defense in Depth.

### 5. Auditing und Logging
- **Auditing**: Erfassung sicherheitsrelevanter Ereignisse.
- **Logging**: Verwendung von SLF4J und Logback.


### Webgaot

Die Webgoat Aufgaben wurden bereits in unseren Security Books gelöst und ausführlich dokumentiert.

## Erfahrungen und Verbesserungen

Bei der Umsetzung dieses Projekts haben wir gelernt, wie wichtig es ist, regelmässige Sicherheitsüberprüfungen durchzuführen und OWASP-Richtlinien zu befolgen. Nächstes Mal werden wir verstärkt automatisierte Tests einbinden, um Sicherheitslücken schneller zu erkennen.

