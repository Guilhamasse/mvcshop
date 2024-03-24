= Fonctionnement de l'application / Documentation Utilisateur
:icons: font
:models: models
:experimental:
:incremental:
:toc: macro
:toclevels: 3
:toc-title: Table des matières
:window: _blank
:correction!:

// Useful definitions
:asciidoc: http://www.methods.co.nz/asciidoc[AsciiDoc]
:icongit: icon:git[]
:git: http://git-scm.com/[{icongit}]
:plantuml: https://plantuml.com/fr/[plantUML]
:vscode: https://code.visualstudio.com/[VS Code]

ifndef::env-github[:icons: font]
// Specific to GitHub
ifdef::env-github[]
:correction:
:caution-caption: :fire:
:important-caption: :exclamation:
:note-caption: :paperclip:
:tip-caption: :bulb:
:warning-caption: :warning:
:icongit: Git
endif::[]

toc::[]

== Schéma logique de l'application

image::images/schemaLogique.png[schema]

== Page de connexion

Si vous êtes un coach, utilisez l'email et mot de passe suivant:

* Email: coach
* Mot de passe: supercoach*

Si vous êtes un élève, initial ou en alternance, votre email et mot de passe sera fourni par le coach.

image::images/pageConnexion.png[co]

Une fois connecté, l'utilisateur avec le rôle de coach est redirigé vers la création d'une faction, qui se fait en plusieurs étapes.

== Coach

=== Page de création d'une faction (coach)

==== Etape 1

Ici, vous pouvez créer une faction en entrant son nom puis en cliquant sur ``Ajouter Faction``. La faction apparait juste en dessous. Cliquez ensuite sur ``Etape suivante``.

image::images/creationFaction1.png[fac1]

==== Etape 2

Vous pouvez ensuite ajouter les élèves qui vont composer les factions. Vous devez entrer son prénom, nom, son mot passe (un est proposé par défaut), sa faction et son type (INIT pour initial et ALT pour alternant).

image::images/creationFaction2.png[fac2]

==== Etape 3

Vous devez ensuite créer un sujet. Vous pouvez également cliquer sur le lien du projet ou en supprimer un sujet.

image::images/creationFaction3.png[fac3]

==== Etape 4

Cette dernière étape consiste à créer automatiquement les équipes avec les élèves dedans, selon leur fiche joueur. Les équipes sont crée de façon à être le plus équitable possible.

Vous pouvez créer les équipes seulement si tous les élèves ont rempli leur fiche joueur. L'état de chaque élève est affiché en dessous dans la liste des étudiants.

image::images/creationFaction4.png[fac4]

=== Page d'accueil d'un coach

Sur cette page d'accueil destiné au Coach, vous avez la liste des élèves qui n'ont pas encore d'équipe, la possibilité de supprimer un élève. Vous pouvez également gérer les sujets, les activités, ou vous déconnecter.

image::images/homepageCoach.png[homeC]

== Elève (initial ou alternant)

=== Fiche Joueur

Une fois qu'un élève s'est connecté, il est redirigé vers la page pour remplir le formulaire de la fiche joueur.
Il faut entrer son pseudo, créez sa fiche joueur puis cliquer sur ``Envoyer``.

image::images/ficheJoueur.png[ficheJ]
 
=== Page d'accueil d'un élève en initial sans sujet.

Ici, on peut voir que l'élève n'a pas encore été assigné à un sujet par le coach. Il peut donc voter pour le sujet qu'il préfère parmi la liste des sujets proposés.

> Pour pouvoir voter pour un sujet, il faut d'abord être assigné à une équipe.

image::images/homepageInit.png[homeI]

=== Page d'accueil d'un élève en initial avec un sujet.

Une fois que l'élève à été affecter à un sujet ...

image::images/homepageInitAvecSujet.png[homeIAS]
