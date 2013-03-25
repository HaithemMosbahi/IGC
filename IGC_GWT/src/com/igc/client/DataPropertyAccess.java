package com.igc.client;

import com.google.gwt.editor.client.Editor.Path;
import com.igc.shared.Data;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface DataPropertyAccess extends PropertyAccess<Data> {
    ValueProvider<Data, Integer> data1();
 
    ValueProvider<Data, String> name();
 
    @Path("name")
    ModelKeyProvider<Data> nameKey();
  }