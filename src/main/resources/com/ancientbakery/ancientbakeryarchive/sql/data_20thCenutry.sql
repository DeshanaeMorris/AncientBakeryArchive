-- Recipe 24: Lemon Cream Pie
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Lemon Cream Pie',
           5,
           'For a five-pai quantity, put 1/2 gallon of gala to boil with 1/4 pound of zakhari. Then dissolve in a little cold gala 6 tablespoons of kolla (cornstarch), 8 krokous avgon, and a little extract from lemon. Mix well and combine with the boiling gala. Pour the krema into the pai dishes prepared with baked pasta, and let cool. Cover with marengka, garnish, and put in the oven for one minute so the marengka dries.',
           'Heat 2 quarts of whole milk with sugar in a heavy saucepan until nearly boiling. In a separate bowl, dissolve cornstarch in a small amount of cold milk, then whisk in 8 egg yolks and lemon extract. Pour slowly into hot milk, whisking constantly. Stir over medium heat until thickened to a velvety custard. Pour into pre-baked pie crusts and let cool until set. Cover with meringue and bake in a hot oven at 425°F for just one minute to set and lightly brown the meringue.',
           'Bake meringue at 220°C (425°F) for 1 minute only',
           5,
           'Michael Gkines, Megale amerikanike mageirike dia mageirous kai oikogeneias (1917)',
           NULL
       );
-- New ingredients for Recipe 24
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('cornstarch', 'other');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('lemon extract', 'other');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('pie crust', 'grain');
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('meringue', 'other');
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (24, 24, 2, 'quart');   -- milk
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (24, 21, 115, 'g');     -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (24, 42, 50, 'g');      -- cornstarch
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (24, 11, 8, 'whole');   -- egg yolks
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (24, 57, 1, 'tsp');     -- lemon extract
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (24, 58, 1, 'whole');   -- pie crust
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (24, 59, 1, 'whole');   -- meringue

-- Recipe 25: Soft Gingerbread
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Soft Gingerbread',
           5,
           'Six teacups of flour, three cups of molasses, three cups of cream, two of butter, one tablespoon of pearlash, and the same of ginger. Bake in a quick oven about half an hour.',
           'Preheat oven to 350°F. Sift together flour and ginger in a large bowl. Dissolve baking soda and cream of tartar in the cream. Cut butter into dry ingredients and blend thoroughly. Stir in cream mixture and molasses. Pour into two greased 9-inch pans. Bake for 30-35 minutes or until a cake tester comes out clean.',
           'Bake at 175°C (350°F) for 30-35 minutes',
           12,
           'Sarah Josepha Hale, The Good Housekeeper (1841), Old Sturbridge Village',
           NULL
       );
-- New ingredients for Recipe 25
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('molasses', 'sweetener');      -- 59
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('cream of tartar', 'rising agent'); -- 60
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (25, 1, 540, 'g');      -- flour
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (25, 59, 540, 'ml');    -- molasses
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (25, 30, 540, 'ml');    -- cream
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (25, 10, 340, 'g');     -- butter
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (25, 46, 1, 'tsp');     -- baking powder
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (25, 60, 1, 'tsp');     -- cream of tartar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (25, 17, 1, 'tbsp');    -- ginger

-- Recipe 26: Pear Marmalade
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Pear Marmalade',
           5,
           'Boil the pear with the skins on. When soft, rub them through a sieve and to each pound of pulp three quarters of a pound of brown sugar. Stew it over a slow fire till it becomes a thick jelly. It should be stirred constantly.',
           'Clean pears and boil whole until soft. Cool the pears then push through a wire sieve or ricer. To every 2 cups of sieved pears add 1.5 cups of brown sugar and stir well. Optionally add 1 teaspoon powdered ginger per 2 cups of pear puree. Place in a saucepan and cook over medium heat, stirring frequently, until the marmalade slightly sticks on a spoon and does not dissolve in cold water. Place in a jar and store.',
           'Cook on stovetop over medium heat stirring constantly until thick',
           6,
           'Anonymous, The American Housewife (1841), Old Sturbridge Village',
           NULL
       );
-- New ingredients for Recipe 26
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('pears', 'fruit');             -- 61
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('brown sugar', 'sweetener');   -- 62
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (26, 61, 6, 'whole');   -- pears
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (26, 62, 300, 'g');     -- brown sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (26, 17, 1, 'tsp');     -- ginger

-- Recipe 27: Chuck Wagon Cornbread
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Chuck Wagon Cornbread',
           5,
           'CORN BREAD OR HOE CAKE. 1 quart meal. 2 tablespoonfuls melted lard. 2 teaspoonfuls salt. Sufficient warm water (but not hot enough to scald meal) or warm milk to make a thick batter.',
           'Preheat oven to 400°F and grease a 10-12 inch cast iron skillet well with lard. Mix cornmeal and salt together in a large bowl. Heat milk until just warm to the touch and melt the lard. Pour about half the warm milk into the cornmeal and stir. Add more milk a little at a time until batter is slightly thicker than pancake batter. Stir in melted lard. Pour batter into the greased skillet and smooth out. Bake for about 40 minutes until the edges start to pull away from the pan.',
           'Bake at 205°C (400°F) for 40 minutes',
           8,
           'Manual for Army Cooks (1896), Tasting History',
           NULL
       );
-- New ingredients for Recipe 27
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('cornmeal', 'grain');          -- 63
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (27, 63, 580, 'g');     -- cornmeal
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (27, 20, 2, 'tsp');     -- salt
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (27, 24, 700, 'ml');    -- milk
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (27, 19, 2, 'tbsp');    -- lard

-- Recipe 28: Indian Pudding
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Baked Indian Pudding',
           5,
           'Indian pudding is good baked. Scald a quart of milk, (skimmed milk will do,) and stir in seven table spoonfuls of sifted Indian meal, a tea-spoonful of salt, a teacupful of molasses, and a great spoonful of ginger, or sifted cinnamon. Bake three or four hours. If you want whey, you must be sure and pour in a little cold milk, after it is all mixed.',
           'Preheat oven to 300°F and butter a 9x9 inch baking dish. Scald 1 quart of milk by heating to 180°F, stirring frequently. Whisk cornmeal and salt together in a bowl. Once milk reaches temperature, reduce heat and stir in cornmeal and salt. Cook for 10 minutes stirring constantly. Stir in molasses and cook for another 2 minutes. Stir in ginger or cinnamon and cook until smooth. Pour into prepared baking dish and pour in remaining half cup of cold milk without stirring. Bake for 3-4 hours. Let cool completely then scoop into serving dishes and top with whipped cream.',
           'Bake at 150°C (300°F) for 3-4 hours',
           6,
           'Lydia Child, The American Frugal Housewife (1829), Tasting History',
           NULL
       );
-- No new ingredients for Recipe 28
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (28, 24, 1, 'quart');   -- milk
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (28, 63, 80, 'g');      -- cornmeal
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (28, 20, 1, 'tsp');     -- salt
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (28, 59, 180, 'g');     -- molasses
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (28, 17, 1, 'tsp');     -- ginger
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (28, 30, 120, 'ml');    -- cream

-- Recipe 29: Gelée Macédoine
INSERT INTO Recipes (name, era_id, original_text, modernized_text, temperature_description, base_portion, sources, image_url)
VALUES (
           'Gelée Macédoine',
           5,
           'This is made with any kind of gelée; however, gelée made with Champagne or sherry is preferable. Any of the delicate fruits of the season, such as grapes, cherries, peaches, strawberries, raspberries, mulberries, currants (on their stems), plums, and orange sections, or preserved fruits such as brandied cherries and peaches, are tastefully imbedded in the gelée, so as to show their forms and colors to best advantage.',
           'Sprinkle gelatin over cold water and stir to distribute. Set aside to hydrate. Make a champagne syrup by simmering 1 cup champagne with sugar until dissolved then remove from heat. Liquefy gelatin in a bowl of hot water then stir into the hot syrup. Strain through a cloth. Let cool to room temperature then stir in remaining champagne. Rinse mold with cold water. Arrange fruit in layers in the mold, adding room temperature gelatin mixture to cover each layer and chilling 10-15 minutes between layers to partially set. Refrigerate at least 8 hours to fully set. Dip mold briefly in hot water then turn out onto a serving dish.',
           'No baking - refrigerate at least 8 hours to set',
           6,
           'Mrs. Mary F. Henderson, Practical Cooking and Dinner Giving (1877), Tasting History',
           NULL
       );
-- New ingredients for Recipe 29
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('champagne', 'other');         -- 64
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('grapes', 'fruit');            -- 65
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('raspberries', 'fruit');       -- 66
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('blueberries', 'fruit');       -- 67
INSERT OR IGNORE INTO Ingredients (name, category) VALUES ('mandarin oranges', 'fruit');  -- 68
-- Recipe_Ingredients
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (29, 49, 21, 'g');      -- gelatin
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (29, 5, 175, 'ml');     -- water
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (29, 64, 750, 'ml');    -- champagne
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (29, 21, 150, 'g');     -- sugar
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (29, 65, 100, 'g');     -- grapes
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (29, 66, 100, 'g');     -- raspberries
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (29, 67, 100, 'g');     -- blueberries
INSERT INTO Recipe_Ingredients (Recipes_ID, Ingredients_ID, Quantity, Unit) VALUES (29, 68, 100, 'g');     -- mandarin oranges