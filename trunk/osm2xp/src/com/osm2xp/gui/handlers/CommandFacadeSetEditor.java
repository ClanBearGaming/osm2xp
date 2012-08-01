package com.osm2xp.gui.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;

import com.osm2xp.gui.dialogs.FacadeSetEditorDialog;

/**
 * CommandFacadeSetEditor.
 * 
 * @author Benjamin Blanchet
 * 
 */
public class CommandFacadeSetEditor implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		DirectoryDialog dialog = new DirectoryDialog(Display.getCurrent()
				.getActiveShell());

		String fileName = dialog.open();

		if (fileName != null) {
			FacadeSetEditorDialog facadeSetEditorDialog = new FacadeSetEditorDialog(
					Display.getCurrent().getActiveShell(), fileName);
			facadeSetEditorDialog.open();

		}

		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {

	}

}
