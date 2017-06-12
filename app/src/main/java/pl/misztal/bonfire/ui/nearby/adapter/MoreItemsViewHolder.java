package pl.misztal.bonfire.ui.nearby.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.misztal.bonfire.ExceptionHandler;
import pl.misztal.bonfire.R;
import pl.misztal.bonfire.model.AdditionalItemsLoadable;
import timber.log.Timber;

/**
 * Created by kmisztal on 12.06.2017.
 *
 * @author Krzysztof Misztal
 */

public class MoreItemsViewHolder extends RecyclerView.ViewHolder {

    private final View.OnClickListener onClickListener;

    @BindView(R.id.info)
    ViewGroup info;

    @BindView(R.id.info_text)
    TextView infoText;

    @BindView(R.id.action_button)
    Button downloadButton;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    static MoreItemsViewHolder inflate(LayoutInflater inflater, ViewGroup parent,
                                       View.OnClickListener onClickListener) {

        View view = inflater.inflate(R.layout.card_item_more_items, parent, false);
        return new MoreItemsViewHolder(view, onClickListener);
    }

    private MoreItemsViewHolder(View itemView, View.OnClickListener onClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.onClickListener = onClickListener;
    }

    void bind(AdditionalItemsLoadable itemsLoadable, ExceptionHandler exceptionHandler) {
        Timber.d("Rendering state: %s", itemsLoadable.toString());
        if (itemsLoadable.isLoading()) {
            info.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            info.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);

            if (itemsLoadable.getLoadingError() != null) {
                String message = exceptionHandler.getMessageError(itemsLoadable.getLoadingError());
                infoText.setText(message);
                downloadButton.setText(R.string.try_again);
            } else {
                infoText.setText(itemView.getContext().getString(R.string.more_items, itemsLoadable.getMoreItemsAvailableCount()));
                downloadButton.setText(R.string.download);
            }

        }
    }

    @OnClick(R.id.action_button)
    void onClick(View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}
