# catapp

App created for own needs to record all medication doses and supplements. First version of UI is made by Vue PWA, but planning made UI also with Flutter or React Native for better user experience to get notifications example. iOs does not accept notifications of PWA applications like Android. Or maybe it is time to get Android, who knows :) It is quite hard after 14 years using iOs...

Simplified database structure:

Cats:
- id
- name

Doses:
- id
- amount
- date
- cat
- item

item:
- id
- doseType (pcs, ml, g etc., types handled in UI, stored as string / varchar(255))
- note

Healthnotes (wife's hope to write some healthnote records like eye infectio etc.)
- id
- date
- note
- cat

Also vaccinations are coming. 
