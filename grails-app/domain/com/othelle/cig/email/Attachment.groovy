package com.othelle.cig.email

/**
 * User: Vasily Vlasov
 * Date: 21.09.12
 */
class Attachment implements Serializable{
    String name
    String path

    @Override
    String toString() {
        return name
    }
}
