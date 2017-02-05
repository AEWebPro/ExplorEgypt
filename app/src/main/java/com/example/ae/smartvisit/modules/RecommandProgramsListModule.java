package com.example.ae.smartvisit.modules;



public class RecommandProgramsListModule {
    String name;
    int imge;

    public RecommandProgramsListModule(String name, int imge)
    {
        this.name=name;
        this.imge=imge;

    }

    public String getName() {
        return name;
    }

    public int getImge() {
        return imge;
    }
}
