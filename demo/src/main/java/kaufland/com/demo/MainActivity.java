package kaufland.com.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import kaufland.com.coachmarklibrary.CoachmarkViewBuilder;
import kaufland.com.coachmarklibrary.renderer.actiondescription.LeftOfCircleActionDescriptionRenderer;
import kaufland.com.coachmarklibrary.renderer.animation.ConcentricCircleAnimationRenderer;
import kaufland.com.coachmarklibrary.renderer.buttonrenderer.DismissOnTouchNoButtonRenderer;
import kaufland.com.coachmarklibrary.renderer.buttonrenderer.OkBelowDescriptionButtonRenderer;
import kaufland.com.coachmarklibrary.renderer.buttonrenderer.UnderlyingActionButtonRenderer;

public class MainActivity extends AppCompatActivity implements DemoClickListener {

    private RecyclerView mRecyclerView;

    private FloatingActionButton mFab;

    private DemoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new DemoAdapter();
        mAdapter.setClickListener(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mFab = findViewById(R.id.fab);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupWithButtonBelowDescription(view);
            }
        });

        findViewById(R.id.fabTop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupWithButtonBelowDescription(view);
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        setupCoachmark(view, this);
    }

    private void setupWithoutButtons(View clickedView) {
        LayoutInflater mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View actionDescription = mInflater.inflate(R.layout.test_action_description, null);
        View description = mInflater.inflate(R.layout.test_description, null);

        new CoachmarkViewBuilder(MainActivity.this)
                .withActionDescription(actionDescription)
                .withDescription(description)
                .withButtonRenderer(new DismissOnTouchNoButtonRenderer.Builder().build())
                .buildAroundView(clickedView)
                .show();
    }

    private void setupWithButtonBelowDescription(View clickedView) {
        LayoutInflater mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View actionDescription = mInflater.inflate(R.layout.test_action_description, null);
        View description = mInflater.inflate(R.layout.test_description, null);

        new CoachmarkViewBuilder(MainActivity.this)
                .withActionDescription(actionDescription)
                .withDescription(description)
                .withButtonRenderer(new OkBelowDescriptionButtonRenderer.Builder().withBorder(4, null).withOkButton("Ok", null).build())
                .buildAroundView(clickedView).show();
    }

    private void setupCoachmark(View clickedView, DemoClickListener clickListener) {
        LayoutInflater mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View actionDescription = mInflater.inflate(R.layout.test_action_description, null);
        View description = mInflater.inflate(R.layout.test_description, null);

//        OkAndCancelAtRightCornerButtonRenderer buttonRenderer = new OkAndCancelAtRightCornerButtonRenderer.Builder(this)
//                .withCancelButton("Cancel", new CoachmarkClickListener() {
//                    @Override
//                    public boolean onClicked() {
//                        Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_LONG).show();
//                        return true;
//                    }
//                })
//                .withOkButton("OK", new CoachmarkClickListener() {
//                    @Override
//                    public boolean onClicked() {
//                        Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_LONG).show();
//                        return true;
//                    }
//                })
//                .build();

        UnderlyingActionButtonRenderer renderer = new UnderlyingActionButtonRenderer.Builder().withUnderlyingAction(new UnderlyingActionButtonRenderer.UnderlyingAction() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "Underlying Action", Toast.LENGTH_LONG).show();
            }
        }).build();

        new CoachmarkViewBuilder(MainActivity.this)
                .withActionDescriptionRenderers(new LeftOfCircleActionDescriptionRenderer())
                .withAnimationRenderer(new ConcentricCircleAnimationRenderer.Builder().withDuration(500).build())
                .withActionDescription(actionDescription)
                .withDescription(description)
                .withButtonRenderer(renderer)
                .buildAroundView(clickedView).show();
    }
}