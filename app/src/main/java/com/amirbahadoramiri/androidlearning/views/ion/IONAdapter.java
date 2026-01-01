package com.amirbahadoramiri.androidlearning.views.ion;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.models.Character;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;

import java.util.List;

public class IONAdapter extends RecyclerView.Adapter<IONAdapter.IONViewHolder> {

    List<com.amirbahadoramiri.androidlearning.models.Character> characterList;

    public IONAdapter(List<Character> characterList) {
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public IONViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IONViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ion, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IONViewHolder holder, int position) {
        holder.onBind(holder);
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public class IONViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView textview;
        SimpleDraweeView draweeView;

        public IONViewHolder(@NonNull View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.textview);
            draweeView = itemView.findViewById(R.id.my_image_view);
        }

        public void onBind(IONViewHolder holder) {
            textview.setText(characterList.get(holder.getAbsoluteAdapterPosition()).getName());

//            ViewPropertyTransition.Animator customAnimator = view -> {
//                view.setAlpha(0f);
//                view.setScaleX(0.7f);
//                view.setScaleY(0.7f);
//
//                view.animate()
//                        .alpha(1f)
//                        .scaleX(1f)
//                        .scaleY(1f)
//                        .setDuration(800)
//                        .setInterpolator(new DecelerateInterpolator())
//                        .start();
//            };
//
//            Glide.with(holder.itemView.getContext())
//                    .load(characterList.get(holder.getAbsoluteAdapterPosition()).getImage())
//                    .transition(GenericTransitionOptions.with(customAnimator))
//                    .into(draweeView);







//            Picasso.get()
//                    .load(characterList.get(holder.getAbsoluteAdapterPosition()).getImage())
//                    .noFade()
//                    .into(draweeView,new Callback() {
//                        @Override
//                        public void onSuccess() {
//                            draweeView.setAlpha(0f);
//                            draweeView.setScaleX(0.7f);
//                            draweeView.setScaleY(0.7f);
//
//                            draweeView.animate()
//                                    .alpha(1f)
//                                    .scaleX(1f)
//                                    .scaleY(1f)
//                                    .setDuration(800)
//                                    .setInterpolator(new DecelerateInterpolator())
//                                    .start();
//                        }
//
//                        @Override
//                        public void onError(Exception e) {
//                        }
//                    });




            Uri uri = Uri.parse(characterList.get(holder.getAbsoluteAdapterPosition()).getImage());

//            1.
//            GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(itemView.getResources())
//                    .setFadeDuration(1500)
//                    .build();
//
//            draweeView.setHierarchy(hierarchy);
//            draweeView.setImageURI(uri);



//            2.
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(ImageRequest.fromUri(uri))
                    .setOldController(draweeView.getController())  // برای reuse
                    .setControllerListener(new BaseControllerListener<>() {
                        @Override
                        public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                            // تصویر لود شد – انیمیشن scale + fade
                            draweeView.setAlpha(0f);
                            draweeView.setScaleX(0.7f);
                            draweeView.setScaleY(0.7f);

                            draweeView.animate()
                                    .alpha(1f)
                                    .scaleX(1f)
                                    .scaleY(1f)
                                    .setDuration(800)
                                    .start();
                        }
                    })
                    .build();
            draweeView.setController(controller);
        }
    }
}
