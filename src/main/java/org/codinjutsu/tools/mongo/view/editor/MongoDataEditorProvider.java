/*
 * Copyright (c) 2018 David Boissier.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codinjutsu.tools.mongo.view.editor;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.fileEditor.FileEditorProvider;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.codinjutsu.tools.mongo.logic.MongoManager;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

public class MongoDataEditorProvider implements FileEditorProvider, ApplicationComponent, DumbAware {


    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        return file instanceof MongoObjectFile;
    }

    @NotNull
    @Override
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        MongoObjectFile mongoObjectFile = (MongoObjectFile) file;
        return new MongoDataEditor(project, MongoManager.getInstance(project), mongoObjectFile);
    }

    @Override
    public void disposeEditor(@NotNull FileEditor editor) {
        editor.dispose();
    }

    @Override
    public void initComponent() {

    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public FileEditorState readState(@NotNull Element sourceElement, @NotNull Project project, @NotNull VirtualFile file) {
        return FileEditorState.INSTANCE;
    }

    @Override
    public void writeState(@NotNull FileEditorState state, @NotNull Project project, @NotNull Element targetElement) {

    }

    @NotNull
    @Override
    public String getEditorTypeId() {
        return "MongoData";
    }

    @NotNull
    @Override
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "MongoPlugin.MongoEditorProvider";
    }
}
