package com.impossibl.postgres.protocol;

import static java.util.Arrays.asList;

import java.io.IOException;

import com.impossibl.postgres.Context;
import com.impossibl.postgres.types.Registry;
import com.impossibl.postgres.types.Type;
import com.impossibl.postgres.utils.DataInputStream;

public class ParameterDescriptionMP implements MessageProcessor {

	@Override
	public void process(Protocol proto, DataInputStream in, Context context) throws IOException {

		short paramCount = in.readShort();

		Type[] paramTypes = new Type[paramCount];
		
		for(int c=0; c < paramCount; ++c) {
			
			int paramTypeId = in.readInt();
			paramTypes[c] = Registry.loadType(paramTypeId);
		}
		
		context.setParameterDescriptions(asList(paramTypes));
	}

}
