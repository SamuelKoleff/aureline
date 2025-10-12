INSERT INTO products (id, name, model, description, price, is_active, gender, type, brand)
VALUES
(1, 'Remera básica negra', 'RB-001', 'Remera de algodón negra básica', 3500.00, true, 'HOMBRE', 'REMERA', 'Nike'),
(2, 'Remera básica blanca', 'RB-002', 'Remera de algodón blanca básica', 3500.00, true, 'HOMBRE', 'REMERA', 'Adidas'),
(3, 'Pantalón jean azul', 'PJ-001', 'Jean azul clásico', 12000.00, true, 'HOMBRE', 'PANTALON', 'Levis'),
(4, 'Pantalón jogger gris', 'PJ-002', 'Jogger gris deportivo', 9500.00, true, 'HOMBRE', 'PANTALON', 'Puma'),
(5, 'Zapatillas running', 'ZR-001', 'Zapatillas para correr', 25000.00, true, 'UNISEX', 'ZAPATOS', 'Nike'),
(6, 'Musculosa deportiva', 'MD-001', 'Musculosa de entrenamiento', 4000.00, true, 'MUJER', 'MUSCULOSA', 'Adidas'),
(7, 'Campera rompeviento', 'CR-001', 'Campera liviana impermeable', 18000.00, true, 'UNISEX', 'CAMPERA', 'The North Face'),
(8, 'Vestido verano', 'VV-001', 'Vestido de verano floreado', 15000.00, true, 'NINA', 'VESTIDO', 'Zara'),
(9, 'Buzo canguro', 'BC-001', 'Buzo canguro negro', 11000.00, true, 'HOMBRE', 'BUZO', 'Quiksilver'),
(10, 'Buzo oversize', 'BO-001', 'Buzo oversize beige', 12500.00, true, 'MUJER', 'BUZO', 'H&M'),
(11, 'Cinturón cuero', 'CC-001', 'Cinturón de cuero marrón', 5000.00, true, 'HOMBRE', 'ACCESORIO', 'Prüne'),
(12, 'Cartera de mano', 'CM-001', 'Cartera de mano elegante', 20000.00, true, 'MUJER', 'ACCESORIO', 'Guess'),
(13, 'Zapatillas urbanas', 'ZU-001', 'Zapatillas urbanas negras', 21000.00, true, 'UNISEX', 'ZAPATOS', 'Converse'),
(14, 'Vestido formal', 'VF-001', 'Vestido negro formal', 30000.00, true, 'MUJER', 'VESTIDO', 'Mango'),
(15, 'Pantalón cargo', 'PC-001', 'Pantalón cargo verde', 13500.00, true, 'HOMBRE', 'PANTALON', 'Dockers'),
(16, 'Musculosa básica', 'MB-001', 'Musculosa de algodón blanca', 3500.00, true, 'NINO', 'MUSCULOSA', 'Gap Kids'),
(17, 'Campera de cuero', 'CCU-001', 'Campera de cuero negra', 45000.00, true, 'HOMBRE', 'CAMPERA', 'Prüne'),
(18, 'Zapatillas trekking', 'ZT-001', 'Zapatillas para trekking', 38000.00, true, 'UNISEX', 'ZAPATOS', 'Salomon'),
(19, 'Pantalón deportivo', 'PD-001', 'Pantalón deportivo liviano', 8900.00, true, 'MUJER', 'PANTALON', 'Reebok'),
(20, 'Remera estampada', 'RE-001', 'Remera con diseño gráfico', 5000.00, true, 'UNISEX', 'REMERA', 'Uniqlo');


INSERT INTO product_variants (id, sku, size, color, stock, price, is_active, version, product_id)
VALUES
(1, 'RB001-BLK-M', 'M', 'Negro', 50, 3500.00, true, 0, 1),
(2, 'RB001-BLK-L', 'L', 'Negro', 30, 3500.00, true, 0, 1),
(3, 'PJ001-AZ-M', 'M', 'Azul', 20, 12000.00, true, 0, 3),
(4, 'PJ002-GR-S', 'S', 'Gris', 15, 9500.00, true, 0, 4),
(5, 'ZR001-UNI-42', '42', 'Negro', 25, 25000.00, true, 0, 5),
(6, 'MD001-WHT-S', 'S', 'Blanco', 40, 4000.00, true, 0, 6),
(7, 'BC001-BLK-M', 'M', 'Negro', 18, 11000.00, true, 0, 9),
(8, 'ZU001-BLK-40', '40', 'Negro', 10, 21000.00, true, 0, 13),
(9, 'VF001-BLK-M', 'M', 'Negro', 8, 30000.00, true, 0, 14),
(10, 'ZT001-GRN-43', '43', 'Verde', 12, 38000.00, true, 0, 18);

INSERT INTO product_images (id, url, product_id, alt_text)
VALUES
(1, '/aureline-images/remera_negra.jpg', 1, 'Remera básica negra'),
(2, '/aureline-images/remera_blanca.jpg', 2, 'Remera básica blanca'),
(3, '/aureline-images/jean_azul.jpg', 3, 'Pantalón jean azul'),
(4, '/aureline-images/jogger_gris.jpg', 4, 'Jogger gris'),
(5, '/aureline-images/zapatillas_running.jpg', 5, 'Zapatillas running'),
(6, '/aureline-images/musculosa.jpg', 6, 'Musculosa deportiva'),
(7, '/aureline-images/campera_rompeviento.jpg', 7, 'Campera rompeviento'),
(8, '/aureline-images/vestido_verano.jpg', 8, 'Vestido de verano'),
(9, '/aureline-images/buzo_canguro.jpg', 9, 'Buzo canguro negro'),
(10, '/aureline-images/buzo_oversize.jpg', 10, 'Buzo oversize beige');
