package com.mutant.api.model.entities.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BitmapResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private int[][] imageBitmap;
}
