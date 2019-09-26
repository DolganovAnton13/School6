package com.yugorsk.school6.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.yugorsk.school6.R;
import com.yugorsk.school6.databinding.FragmentAboutSchoolBinding;
import com.yugorsk.school6.view.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAboutSchool extends Fragment {


    private FragmentAboutSchoolBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_school, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).setToolbar(binding.toolbarAboutSchool,"");
        ((MainActivity)getActivity()).navigationView.getMenu().getItem(1).setChecked(true);

        setSpinner();
    }

    @Override
    public void onDestroyView() {
        ((MainActivity)getActivity()).setToolbar(null,"");
        super.onDestroyView();
    }

    private void setSpinner()
    {
        try {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.list_spinner_menu, R.layout.layout_spinner_title);
            adapter.setDropDownViewResource(R.layout.layout_spinner_list);

            binding.spinnerAboutSchool.setAdapter(adapter);

            binding.spinnerAboutSchool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    FragmentManager fragmentManager = getChildFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    int width = (int) getResources().getDimension(R.dimen.sizeSpinner);
                    switch (i) {

                        case 0:
                            binding.spinnerAboutSchool.setLayoutParams(new Toolbar.LayoutParams(width, Toolbar.LayoutParams.WRAP_CONTENT));
                            FragmentAboutSchool1 f1 = new FragmentAboutSchool1();
                            fragmentTransaction.add(R.id.fragment_container, f1);
                            fragmentTransaction.commit();
                            break;
                        case 1:
                            binding.spinnerAboutSchool.setLayoutParams(new Toolbar.LayoutParams(width, Toolbar.LayoutParams.WRAP_CONTENT));
                            FragmentAboutSchool2 f2 = new FragmentAboutSchool2();
                            fragmentTransaction.add(R.id.fragment_container, f2);
                            fragmentTransaction.commit();
                            break;
                        case 2:
                            binding.spinnerAboutSchool.setLayoutParams(new Toolbar.LayoutParams(width, Toolbar.LayoutParams.WRAP_CONTENT));
                            FragmentAboutSchool3 f3 = new FragmentAboutSchool3();
                            fragmentTransaction.add(R.id.fragment_container, f3);
                            fragmentTransaction.commit();
                            break;
                        case 3:
                            binding.spinnerAboutSchool.setLayoutParams(new Toolbar.LayoutParams(width, Toolbar.LayoutParams.WRAP_CONTENT));
                            FragmentAboutSchool4 f4 = new FragmentAboutSchool4();
                            fragmentTransaction.add(R.id.fragment_container, f4);
                            fragmentTransaction.commit();
                            break;
                        case 4:
                            binding.spinnerAboutSchool.setLayoutParams(new Toolbar.LayoutParams(width, Toolbar.LayoutParams.WRAP_CONTENT));
                            FragmentAboutSchool5 f5 = new FragmentAboutSchool5();
                            fragmentTransaction.add(R.id.fragment_container, f5);
                            fragmentTransaction.commit();
                            break;
                        case 5:
                            binding.spinnerAboutSchool.setLayoutParams(new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT));
                            FragmentAboutSchool6 f6 = new FragmentAboutSchool6();
                            fragmentTransaction.add(R.id.fragment_container, f6);
                            fragmentTransaction.commit();
                            break;
                        case 6:
                            binding.spinnerAboutSchool.setLayoutParams(new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT));
                            FragmentAboutSchool7 f7 = new FragmentAboutSchool7();
                            fragmentTransaction.add(R.id.fragment_container, f7);
                            fragmentTransaction.commit();
                            break;
                        case 7:
                            binding.spinnerAboutSchool.setLayoutParams(new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT));
                            FragmentAboutSchool8 f8 = new FragmentAboutSchool8();
                            fragmentTransaction.add(R.id.fragment_container, f8);
                            fragmentTransaction.commit();
                            break;
                        case 8:
                            binding.spinnerAboutSchool.setLayoutParams(new Toolbar.LayoutParams(width, Toolbar.LayoutParams.WRAP_CONTENT));
                            FragmentAboutSchool9 f9 = new FragmentAboutSchool9();
                            fragmentTransaction.add(R.id.fragment_container, f9);
                            fragmentTransaction.commit();
                            break;
                        case 9:
                            binding.spinnerAboutSchool.setLayoutParams(new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT));
                            FragmentAboutSchool10 f10 = new FragmentAboutSchool10();
                            fragmentTransaction.add(R.id.fragment_container, f10);
                            fragmentTransaction.commit();
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        } catch (Exception ex) {
            Toast.makeText(getContext(), "Ошибка", Toast.LENGTH_SHORT).show();
        }
    }
}
