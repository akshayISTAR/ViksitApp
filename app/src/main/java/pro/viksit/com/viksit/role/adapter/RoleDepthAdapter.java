package pro.viksit.com.viksit.role.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import pro.viksit.com.viksit.R;
import pro.viksit.com.viksit.role.pojo.RoleChild;
import pro.viksit.com.viksit.role.pojo.RoleParent;
import pro.viksit.com.viksit.role.viewholder.RoleNameViewHolder;
import pro.viksit.com.viksit.role.viewholder.RoleSkillViewHolder;

/**
 * Created by Feroz on 20-03-2017.
 */

public class RoleDepthAdapter extends ExpandableRecyclerAdapter<RoleParent,RoleChild,RoleSkillViewHolder,RoleNameViewHolder> {

    List<RoleParent> roleParents;
    private LayoutInflater mInflater;
    private Context context;
    private int screenWidth,screenHeight;
    private double diagonalInches;


    public RoleDepthAdapter(Context context, @NonNull List<RoleParent> roleParents, int screenWidth,int screenHeight, double diagonalInches) {
        super(roleParents);
        this.roleParents = roleParents;
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.diagonalInches = diagonalInches;
    }

    @NonNull
    @Override
    public RoleSkillViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View recipeView;

        recipeView = mInflater.inflate(R.layout.role_parent_view, parentViewGroup, false);

        return new RoleSkillViewHolder(recipeView, screenWidth, screenHeight, diagonalInches);
    }

    @NonNull
    @Override
    public RoleNameViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View ingredientView;
        ingredientView = mInflater.inflate(R.layout.role_child_view, childViewGroup, false);

        return new RoleNameViewHolder(ingredientView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull RoleSkillViewHolder parentViewHolder, int parentPosition, @NonNull RoleParent parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull RoleNameViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull RoleChild child) {
        childViewHolder.bind(child,parentPosition, childPosition, roleParents);
    }



}
