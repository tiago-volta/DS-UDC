package e3;

public class Ad {
    //Final se utiliza para indicar que una variable no puede cambiar una vez cada vez que se ha asignado
    private final String agency;
    private final AdType adType;
    private float price;
    private final Property property;

    //Constructora, nuevo anucio
    public Ad(String agency, Property property, AdType adType, float price) {
        if (price <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0");
        }
        if (adType != AdType.PURCHASE && adType != AdType.RENTAL) {
            throw new IllegalArgumentException("El tipo de anuncio debe ser válido");
        }
        if (property == null || agency == null) {
            throw new IllegalArgumentException("La propiedad o la agencia no puede ser nula");
        }
        this.agency = agency;
        this.property = property;
        this.price = price;
        this.adType = adType;
    }

    // Constructor de copia
    public Ad(Ad ad) {
        this.property = ad.property;
        this.agency = ad.agency;
        this.adType = ad.adType;
        this.price = ad.price;
    }

    @Override
    public String toString() {
        return agency + "\n" + property.toString() + "\n" + price + "\n" + AdType.PURCHASE;
    }

    public boolean isPropertyEqual(Ad obj) {
        return this.property.equals(obj.property);
    }

    public float getPriceInEuros() {
        return this.price;
    }

    public float priceMetersEuros() {
        if (property.areaEnMetros() > 0) {
            return price / property.areaEnMetros();
        } else
            throw new ArithmeticException("Los metros cuadrados deben ser no nulos");
    }

    //Método para calcular si el precio es correcto
    public boolean isPriceNormal() {
        if (adType == AdType.PURCHASE) {
            float priceSquareMeter = this.priceMetersEuros();
            return priceSquareMeter >= 500 && priceSquareMeter <= 5000;
        } else if (adType == AdType.RENTAL) {
            return price >= 300 && price <= 5000;
        } else
            throw new IllegalArgumentException("Tipo de anuncio no reconocido");
    }

    public void dropPrice(float percentage) {
        if (percentage <= 0 || percentage >= 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100");
        }
        price -= (price * (percentage / 100));
    }

}