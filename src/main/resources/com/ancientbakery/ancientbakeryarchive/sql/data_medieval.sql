-- Recipe 12: Cream Custard Tart
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Doucetes (Cream Custard Tart)',
           2,
           'Take Creme a gode cupfulle, & put it on a straynour, thanne take yolkes of Eyroun, and put ther-to, & a lytel mylke; then strayne it throw a straynour in-to a bolle; then take Sugre y-now, put ther-to, or ellys hony forde faute of Sugre, than coloure it with Safroun; than take thin cofyns, & put it in the ovynne letre, & tat hem ben hardyd; than take a dyssche y-fastenyd on the pelys ende, & pore thin comade in-to the dyssche, & fro the dyssche in-to the cofyns; & whan they don a-ryse wet, teke hem out, ee serue hem forth.',
           'Soak the saffron in 2 tbsp water until the water is deep gold in colour. Add the pastry to a 20cm pie plate or cake tin with a loose bottom, with a depth of 5cm. Bake blind in a preheated oven at 200°C for 15–20 minutes, then remove the filling of dried beans and return the case to the oven at about 160°C for 6–8 minutes until dried out and firm. Beat the egg yolks lightly in a bowl, then beat in the cream, milk, sugar, saffron water and salt. Pour the custard into the pastry case. Bake at 160°C for about 45 minutes or until just set in the centre. Serve warm.',
           'Bake blind at 200°C for 15-20 minutes, then at 160°C for 45 minutes',
           6,
           'British Museum, How to Cook a Medieval Feast',
           NULL
       );
-- New ingredients for Recipe 12
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('shortcrust pastry', 'other');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('double cream', 'dairy');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (12, 36, 1, 'whole');  -- shortcrust pastry
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (12, 11, 6, 'whole');  -- egg yolks
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (12, 37, 350, 'ml');   -- double cream
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (12, 24, 125, 'ml');   -- milk
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (12, 21, 65, 'g');     -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (12, 12, 1, 'tsp');    -- saffron
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (12, 20, 1, 'tsp');    -- salt

-- Recipe 13: Gingerbrede
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Gingerbrede (Good)',
           2,
           'Take goode honey & clarifie it on the fyre, & take fayre paynemayn or wastel brede & grate it, & caste it into the boylenge hony, & stere it well togyder faste with a sklyse that it bren not to the vessell. & than take it doun and put therin ginger, longe pepper & saundres, & tempere it vp with thin handes; & than put hem to a flatt boyste & strawe theron suger, & pick therin clowes rounde aboute by the egge and in the mydes.',
           'Bring honey to a boil, simmer two or three minutes, stir in breadcrumbs with a spatula until uniformly mixed. Remove from heat, stir in ginger, pepper, and saunders. When cool enough to handle, knead to get spices thoroughly mixed. Put in a flat container, squish flat and thin, sprinkle with sugar and put cloves ornamentally around the edge. Leave to let the clove flavor sink in.',
           'No baking required - stovetop only',
           6,
           'Curye on Inglysch p. 154 (Goud Kokery no. 18)',
           NULL
       );
-- New ingredients for Recipe 13
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('pepper', 'spice');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('saunders', 'spice');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('white bread', 'grain');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (13, 35, 1, 'cup');    -- honey
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (13, 32, 1, 'cup');    -- breadcrumbs
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (13, 17, 1, 'tsp');    -- ginger
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (13, 38, 1, 'tsp');    -- pepper
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (13, 39, 1, 'tsp');    -- saunders
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (13, 21, 1, 'tbsp');   -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (13, 16, 1, 'tsp');    -- cloves

-- Recipe 14: Rastons
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Rastons (Sweet Butter Bread)',
           2,
           'Take fayre Flowre, and the whyte of Eyroun, and the yolk, a lytel; than take Warme Berme, and putte al thes to-gederys, and bete hem to-gederys with thin hond tyl it be schort and thikke y-now, and caste Sugre y-now ther-to, and thanne lat reste a whyle; than kaste in a fayre place in the oven, and late bake y-now; and then with a knyf cutte yt round a-boue in maner of a crowne, and kepe the crust that thou kyttyst; and than pyke al the cromys with-ynne to-gederys, an pike hem smal with thin knyf, and saue the sydys and al the cruste hole with-owte; and than caste ther-in clarifiyd Botor, and mille the cromes and the botor to-gederes, and keuere it a-gen with the cruste.',
           'After mixing all ingredients except butter, let the dough rise 45 minutes to an hour. Mold the dough on a greased cookie sheet, let rise a little more. Bake at 350°F for about 1 hour. Cut off top as described, mix insides of loaf with melted butter, and replace top. Second baking is about 5 minutes at the same temperature.',
           'Bake at 350°F (175°C) for 1 hour, then 5 minutes for second bake',
           6,
           'Two Fifteenth Century p. 52/63',
           NULL
       );
-- New ingredients for Recipe 14
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('egg whites', 'protein');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (14, 1, 500, 'g');     -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (14, 40, 2, 'whole');  -- egg whites
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (14, 11, 1, 'whole');  -- egg yolks
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (14, 25, 1, 'tbsp');   -- yeast
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (14, 21, 100, 'g');    -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (14, 10, 200, 'g');    -- butter

-- Recipe 15: Cryspes
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Cryspes (Medieval Fritters)',
           2,
           'Take white of eyroun, milk, and flour, and a little berme, and beat it together, and draw it through a strainer, so that it be running, and not too stiff, and cast suger thereto, and salt; then take a chafer full of fresh grease boiling, and put thine hand in the batter, and let thine batter run down by thy fingers into the chafer; and when it is run together on the chafer, and is enough, take and nym a skimmer, and take it up, and let all the grease run out, and put it on a fair dish, and cast thereon sugar enough, and serve forth.',
           'Beat together egg whites, milk, flour, yeast, sugar and salt into a smooth batter. Pour into a pan of hot oil so that they puff up and brown, turn them, drain them, sprinkle on sugar and serve.',
           'Fry in hot oil until puffed and golden brown on both sides',
           4,
           'Two Fifteenth Century p. 44/61',
           NULL
       );
-- No new ingredients for Recipe 15
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (15, 40, 4, 'whole');  -- egg whites
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (15, 24, 150, 'ml');   -- milk
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (15, 1, 120, 'g');     -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (15, 25, 1, 'tbsp');   -- yeast
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (15, 21, 3, 'tbsp');   -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (15, 20, 1, 'tsp');    -- salt

-- Recipe 16: Creme Boylede
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Creme Boylede (Boiled Cream Pudding)',
           2,
           'Take creme or mylke, and brede of paynemayn, or ellys of tendre brede, and breke it on the creme, or elles in the mylke, an set it on the fyre tyl it be warme hot; and thorw a straynour throwe it, and put it into a fayre potte, an sette it on the fyre, an stere euermore: an whan it is almost y-boylyd, take fayre yolkes of eyron, an draw hem thorw a straynowr and caste hem ther-to, and let hem stonde ouer the fyre tyl it boyle almost, an till it be skylfully thikke; than caste a ladel-ful, or more or lasse, of boter ther-to, an a good quantite of whyte sugre, and a litel salt.',
           'Soak bread in cream. Heat until hot to the touch but not boiling. Pass through a coarse sieve or mash thoroughly. Heat again, stirring constantly. When almost boiling, stir in egg yolks. Keep heating, stirring, not boiling, until it thickens. Stir in butter, sugar, salt. Serve in bowls.',
           'Heat on stovetop over medium heat, do not boil',
           6,
           'Two Fifteenth Century p. 8/52',
           NULL
       );
-- New ingredients for Recipe 16
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('white bread', 'grain');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (16, 41, 8, 'whole');  -- white bread slices
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (16, 30, 1, 'quart');  -- cream
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (16, 11, 8, 'whole');  -- egg yolks
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (16, 10, 6, 'tbsp');   -- butter
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (16, 21, 100, 'g');    -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (16, 20, 1, 'tsp');    -- salt

-- Recipe 17: French Bread
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'French Bread (No-Knead)',
           2,
           'A simple no-knead French bread leavened with yeast, shaped and baked until golden with a crisp crust.',
           'Mix flour, yeast, salt and water together into a shaggy dough. Let rest overnight or for at least 12 hours. Shape into a loaf and let rise for another hour. Bake in a preheated oven until golden with a crisp crust.',
           'Bake at 220°C (425°F) for 30-35 minutes',
           2,
           'Savoring the Past, 18th Century No-Knead French Bread',
           NULL
       );
-- No new ingredients for Recipe 17
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (17, 1, 500, 'g');    -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (17, 25, 1, 'tsp');   -- yeast
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (17, 20, 10, 'g');    -- salt
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (17, 33, 375, 'ml');  -- water