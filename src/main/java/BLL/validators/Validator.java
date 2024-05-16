package BLL.validators;

/**
 * This is a Validator interface that defines a method for validating objects of a generic type.
 * @param <T> The type of the object to be validated.
 */
public interface Validator<T> {

    /**
     * This method validates an object of type T.
     * @param t The object to be validated.
     * @param isInsert A boolean value that indicates whether the operation is an insert operation.
     */
    void validate(T t, boolean isInsert);
}