# catapp

App created for own needs to record all medication doses and supplements. First version of UI is made by Vue PWA, but having plans to made UI also with Flutter or React Native for better user experience to get notifications example. Because iOs does not accept notifications of PWA applications like Android. Or maybe it is time to get Android, who knows :) But it is quite hard after 14 years use of iOs...

Simplified database structure:

Cats:
- name

Doses:
- amount
- date
- cat
- item
- deleted

item:
- doseType (pcs, ml, g etc., types handled in UI, stored as string / varchar(255))
- note
- deleted

Healthnotes (wife's hope to write some healthnote records like eye infectio etc.):
- date
- note
- cat
- deleted

Vaccinations:
- exprirationDate
- name
- deleted

![catapp](https://giphy.com/embed/5vNOymulA3Tgz0UgZ6)
