package pl.kubisiak.githubbrowser.ui.databinding.recyclerview;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.kubisiak.githubbrowser.ui.base.BaseViewModel;
import pl.kubisiak.githubbrowser.ui.databinding.DefaultDataTemplateSelector;


public class BindingAdapterRecyclerView {

    //TODO: add DTS as binding param
    @BindingAdapter({"items"})
    public static void bindItemsThroughViewModelAdapter(@NonNull RecyclerView recyclerView, @Nullable List<BaseViewModel> items) {
        if (items != null ) {
            recyclerView.setAdapter(new ViewModelAdapter(items, DefaultDataTemplateSelector.INSTANCE));
        } else {
            recyclerView.setAdapter(null);
        }
    }

    @BindingAdapter({"items"})
    public static void bindItemsThroughViewModelAdapter(@NonNull RecyclerView recyclerView, @Nullable ObservableList<BaseViewModel> items) {
        ViewModelObserverAdapter oldAdapter = (recyclerView.getAdapter() instanceof ViewModelObserverAdapter) ? (ViewModelObserverAdapter)recyclerView.getAdapter() : null;
        if(oldAdapter != null && oldAdapter.isListSame(items)) {
            //for some reason, binding engine calls change on instance of the list even though only the contents change, not the instance
            //if the adapter is observing this list, it's already handling content changes so we have to ignore the "change" of the list here
            return;
        }
        if (items != null ) {
            recyclerView.setAdapter(new ViewModelObserverAdapter(items, DefaultDataTemplateSelector.INSTANCE));
        } else {
            recyclerView.setAdapter(null);
        }
    }

    @BindingAdapter({"vertical"})
    public static void setRecyclerViewOrientation(@NonNull RecyclerView recyclerView, @Nullable Boolean isVertical) {
        if(isVertical == null) isVertical = true;
        Context recyclerViewContext = recyclerView.getContext();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerViewContext, isVertical?RecyclerView.VERTICAL:RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}
