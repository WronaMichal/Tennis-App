package com.javafee.tenninsapp.model.pojo;

public enum SurfaceCourt {
    GRASS {
        @Override
        public String toString() {
            return "Trawka";
        }
    },
    CLAY {
        @Override
        public String toString() {
            return "Mączka";
        }
    },
    HARD {
        @Override
        public String toString() {
            return "Twarda";
        }
    }
}
